package org.school.backend.application.usecases.impl;

import org.school.backend.application.dto.request.OperationFeeReqDto;
import org.school.backend.application.dto.response.OperationFeeResDto;
import org.school.backend.application.exception.OptFeeDataNotFoundException;
import org.school.backend.application.exception.SavingDataNotFoundException;
import org.school.backend.application.mappers.OptfeeMapper;
import org.school.backend.application.usecases.OperationalFeeUseCase;
import org.school.backend.domain.gateaway.LoggerGateway;
import org.school.backend.domain.gateaway.OperationalFeeGateway;
import org.school.backend.domain.gateaway.StudentLogGateaway;
import org.school.backend.domain.model.OperationalFeeModel;
import org.school.backend.domain.model.StudentModel;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.school.backend.application.utils.DateTimeFormatterConfig.parseDate;

public class OperationalFeeUseCaseImpl implements OperationalFeeUseCase {

    final OperationalFeeGateway operationalFeeGateway;
    private final StudentLogGateaway studentLogGateaway;
    private final LoggerGateway logger;



    public OperationalFeeUseCaseImpl(
            final OperationalFeeGateway operationalFeeGateway,
            final StudentLogGateaway studentLogGateaway,
            final LoggerGateway logger


    ){
        this.operationalFeeGateway = operationalFeeGateway;
        this.studentLogGateaway = studentLogGateaway;
        this.logger = logger;


    }
    @Override
    public Optional<List<OperationFeeResDto>> findAllFee(int page, int rpp, String q, String status, String month, UUID classId) {
        List<OperationalFeeModel> feeModels = operationalFeeGateway.findOperationalfee(page,rpp,q,status,month,classId).orElseThrow(OptFeeDataNotFoundException::new);

        System.out.println(feeModels + "ini usecase findALLFEE");


        Set<UUID> studentIds = feeModels.stream()
                .map(OperationalFeeModel::studentId)
                .collect(Collectors.toSet());

        Map<UUID, StudentModel> studentMap = fetchStudentMap(studentIds);


        List<OperationFeeResDto> dtos = mapToDtos(feeModels, studentMap);

        return Optional.of(dtos);
    }

    @Override
    public void createPayment(OperationFeeReqDto record) {
        logger.info("[Operational Fee Use Case] - Method Create Started: {}", record.toString());
        final OperationalFeeModel optModel = new OperationalFeeModel(
                record.studentId(),
                record.paymentType(),
                record.transactionType(),
                record.description(),
                parseDate(record.transactionDate()),
                record.amount()


        );

        operationalFeeGateway.createFee(optModel);
    }



    private List<OperationFeeResDto> mapToDtos(List<OperationalFeeModel> feeModels, Map<UUID, StudentModel> studentMap) {
        return feeModels.stream()
                .map(fee -> {
                    StudentModel student = studentMap.get(fee.studentId());
                    if (student == null) {
                        logger.warn("[OperationalFeeUseCase] Student not found for ID");
                        throw new SavingDataNotFoundException("Student not found for ID: " + fee.studentId());
                    }
                    return OptfeeMapper.toDto(fee, student);
                })
                .toList();
    }

    private Map<UUID, StudentModel> fetchStudentMap(Set<UUID> studentIds) {
        if (studentIds.isEmpty()) {
            return Map.of();
        }

        List<StudentModel> students = studentLogGateaway.findAllById(studentIds);
        return students.stream()
                .collect(Collectors.toMap(
                        StudentModel::id,
                        Function.identity(),
                        (existing, replacement) -> existing,
                        HashMap::new
                ));
    }

}
