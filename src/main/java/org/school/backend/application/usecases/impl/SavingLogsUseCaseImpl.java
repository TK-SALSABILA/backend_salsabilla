package org.school.backend.application.usecases.impl;

import org.school.backend.application.dto.request.SavingParamDto;
import org.school.backend.application.dto.request.SavingRequestDto;
import org.school.backend.application.dto.response.SavingLogOutputDto;
import org.school.backend.application.exception.SavingDataNotFoundException;
import org.school.backend.application.mappers.SavingMapper;
import org.school.backend.application.usecases.SavingLogsUseCase;
import org.school.backend.domain.gateaway.LoggerGateway;
import org.school.backend.domain.gateaway.SavingLogGateaway;
import org.school.backend.domain.gateaway.StudentLogGateaway;
import org.school.backend.domain.model.SavingModel;
import org.school.backend.domain.model.StudentModel;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.school.backend.application.utils.DateTimeFormatterConfig.parseDate;

public class SavingLogsUseCaseImpl implements SavingLogsUseCase {

    private final SavingLogGateaway savingLogGateaway;
    private final StudentLogGateaway studentLogGateaway;
    private final LoggerGateway logger;

    public SavingLogsUseCaseImpl(
            final SavingLogGateaway savingLogGateaway,
            final StudentLogGateaway studentLogGateaway,
            final LoggerGateway logger
    ){
        this.savingLogGateaway = savingLogGateaway;
        this.studentLogGateaway = studentLogGateaway;
        this.logger = logger;
    }

    @Override
    public Optional<List<SavingLogOutputDto>> findAll(SavingParamDto params) {
        logger.info("[saving use case] - Method Find All Started: {}", params.toString());
        List<SavingModel> savings = savingLogGateaway.findSavings(params.page(), params.rpp(), params.q(), params.status(), params.month(), params.classId())
                .orElseThrow(SavingDataNotFoundException::new);

        logger.info("[saving use case] - Found {} savings from gateway", savings.toString());


        List<SavingLogOutputDto> result = mapLatestSavingToDto(savings);

        logger.info("[saving use case] - Find All response: {} records mapped", result.toString());
        return Optional.of(result);
    }

    @Override
    public void create(final SavingRequestDto record) {
        logger.info("[saving use case] - Method Create Started: {}", record.toString());
       final SavingModel savingModel =  new SavingModel(
               record.studentId(),
               null,
               record.paymentType(),
               record.transactionType(),
               parseDate(record.transactionDate()),
               record.amount(),
               record.description()
        );
        logger.info("[saving use case] - Saving model created: {}", savingModel.toString());

        this.savingLogGateaway.create(savingModel);
        logger.info("[saving use case] - Saving record created successfully for student ID: {}", record.studentId().toString());
    }

    @Override
    public Integer checkBalance(UUID studentId) {
        logger.info("[saving use case] - Method Check Balance Started: studentId={}", studentId.toString());

        Integer balance = this.savingLogGateaway.reduction(studentId);

        logger.info("[saving use case] - Balance checked for student ID : {}", balance.toString());
        return balance;
    }

    private List<SavingLogOutputDto> mapLatestSavingToDto(List<SavingModel> savings) {
        logger.info("[saving use case] - Mapping {} savings to latest DTO per student", savings.toString());
        return getLatestSavingsByStudent(savings).values().stream()
                .map(this::mapToDto)
                .toList();
    }

    private Map<UUID, SavingModel> getLatestSavingsByStudent(List<SavingModel> savings) {
        Map<UUID, SavingModel> latestMap = savings.stream()
                .collect(Collectors.toMap(
                        SavingModel::studentId,
                        Function.identity(),
                        this::getLatestSaving
                ));

        logger.info("[saving use case] - Grouped savings into : {}", latestMap.toString());
        return latestMap;
    }

    private SavingModel getLatestSaving(SavingModel s1, SavingModel s2) {
        return  s2.transactionDate().isAfter(s1.transactionDate()) ? s2 : s1;
    }

    private SavingLogOutputDto mapToDto(SavingModel saving) {
        logger.info("[saving use case] - Mapping saving to DTO for student ID: {}", saving.toString());

        StudentModel student = studentLogGateaway.findById(saving.studentId())
                .orElseThrow(() -> new SavingDataNotFoundException("Student not found for ID: " + saving.studentId()));

        SavingLogOutputDto dto = SavingMapper.toDto(saving, student);

        logger.info("[saving use case] - Successfully mapped saving to DTO: {}", dto.toString());
        return dto;
    }

}
