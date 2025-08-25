package org.school.backend.application.usecases.impl;

import org.school.backend.application.dto.request.TuitionParamDto;
import org.school.backend.application.dto.request.TuitonFeeReqDto;
import org.school.backend.application.dto.response.StudentsLogsOutputDto;
import org.school.backend.application.dto.response.TuitonFeeResDto;
import org.school.backend.application.usecases.TuitionFeeUseCase;
import org.school.backend.domain.gateaway.*;
import org.school.backend.domain.model.StudentModel;
import org.school.backend.domain.model.TuitionFeeModel;


import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.school.backend.application.mappers.TuitionResMapper.toListDto;
import static org.school.backend.application.utils.DateTimeFormatterConfig.parseDate;

public class TuitonFeeUseCaseImpl implements TuitionFeeUseCase {

    final TuitionFeeLogGateaway tuitionFeeLogGateaway;
    final GradeStudentLogGateaway gradeStudentLogGateaway;
    final StudentLogGateaway studentLogGateaway;
    final SavingLogGateaway savingLogGateaway;
    final LoggerGateway logger;


    public TuitonFeeUseCaseImpl(
            final TuitionFeeLogGateaway tuitionFeeLogGateaway,
            final GradeStudentLogGateaway gradeStudentLogGateaway,
            final StudentLogGateaway studentLogGateaway,
            final SavingLogGateaway savingLogGateaway,
            final LoggerGateway logger
    ){
        this.tuitionFeeLogGateaway = tuitionFeeLogGateaway;
        this.gradeStudentLogGateaway = gradeStudentLogGateaway;
        this.studentLogGateaway = studentLogGateaway;
        this.savingLogGateaway = savingLogGateaway;
        this.logger = logger;
    }


    @Override
    public Optional<List<TuitonFeeResDto>> findAllTuition(Object classId, String month) {

        // 1. Ambil ID siswa berdasarkan kelas
        List<UUID> studentIds = getStudentIdsByClass(classId);

        // 2. Buat mapping ID siswa -> Data siswa
        Map<UUID, StudentsLogsOutputDto> studentMap = buildStudentMap(studentIds);

        // 3. Ambil data tuition, atau buat pending jika belum ada
        List<TuitionFeeModel> fees = getOrCreateTuitionFees(studentIds, month);

        // 4. Mapping ke DTO (bisa dipindah ke mapper layer jika mau lebih clean lagi)
        return Optional.of(toListDto(fees, studentMap));
//        return Optional.empty();
    }

    @Override
    public Optional<List<TuitonFeeResDto>> findTuition(TuitionParamDto param) {

        logger.info("[tuition use case] - Method Find Tuition with filters: {}", param.toString());

        // 1. Dapatkan studentId dari classId
        List<UUID> studentIds = getStudentIdsByClass(param.classId());
        logger.info("[tuition use case] - Found students in class {}", studentIds.toString());

        if (studentIds.isEmpty()) {
            logger.info("[tuition use case] - No students found for class {}, returning empty list", param.toString());
            return Optional.of(List.of());
        }

        // 2. Pastikan ada tuition fee PENDING untuk bulan ini
        ensureTuitionFeesExist(studentIds, param.month());
        logger.info("[tuition use case] - Ensured tuition fees exist for month: {}", param.month());

        // 3. Ambil semua tuition fee dengan filter (Specification)
        List<TuitionFeeModel> fees = tuitionFeeLogGateaway.findTuition(
                param.page(),
                param.rpp(),
                param.q(),
                param.status(),
                param.month(),
                param.classId()
        ).orElse(List.of());
        logger.info("[tuition use case] - Retrieved {} tuition records from gateway", fees.toString());

        // 4. Ambil data siswa satu per satu (karena tidak ada findAllById)
        Map<UUID, StudentsLogsOutputDto> studentMap = buildStudentMap(studentIds);
        logger.info("[tuition use case] - Built student map for {} students", studentMap.toString());

        List<TuitonFeeResDto> result = toListDto(fees, studentMap);
        logger.info("[tuition use case] - Mapped {} tuition records to DTOs", result.toString());

        return Optional.of(result);
    }

    @Override
    public void createTuition( TuitonFeeReqDto record) {
        logger.info("[tuition use case] - Method Create Tuition Started: {}", record.toString());
        String status = null;
        UUID studentId = record.studentId();
        Integer amount = record.amount();
        if(!record.transactionDate().isEmpty()){
            status = "SUCCESS";
        }

        if("WITHDRAW".equals(record.paymentType())){
            savingLogGateaway.withDrawSaving(
                    studentId,
                    amount,
                    "Pindah dana ke pembayaran SPP bulan " + record.month()
            );
        }

        final TuitionFeeModel tuitionModel = new TuitionFeeModel(
                record.studentId(),
                record.paymentType(),
                record.transactionType(),
                status,
                parseDate(record.transactionDate()),
                record.month(),
                record.amount()
        );
        logger.info("[tuition use case] - Created tuition model: {}", tuitionModel.toString());
        tuitionFeeLogGateaway.createTuition(tuitionModel);
        logger.info("[tuition use case] - Tuition record created successfully for student ID: {}", studentId.toString());
    }

    private List<UUID> getStudentIdsByClass(Object classId) {
        logger.info("[tuition use case] - Fetching student IDs for class: {}", classId.toString());
        List<UUID> studentIds = gradeStudentLogGateaway.findStudentByClass(classId).studentId();
        logger.info("[tuition use case] - Retrieved {} student IDs from class", studentIds.toString());
        return studentIds;
    }

    private Map<UUID, StudentsLogsOutputDto> buildStudentMap(List<UUID> studentIds) {
        logger.info("[tuition use case] - Building student map for {} students", studentIds.toString());
        return studentIds.stream()
                .map(this::findStudentOrThrow)
                .collect(Collectors.toMap(StudentModel::id, StudentsLogsOutputDto::new));
    }

    private StudentModel findStudentOrThrow(UUID id) {
        logger.info("[tuition use case] - Fetching student data for ID: {}", id.toString());
        return studentLogGateaway.findById(id)
                .orElseThrow(() ->
                {
                    logger.warn("[tuition use case] - Student not found: {}", id.toString());
                    return new RuntimeException("Student not found: " + id);
                });
    }

    private List<TuitionFeeModel> getOrCreateTuitionFees(List<UUID> studentIds, String month) {
        List<TuitionFeeModel> existingFees = tuitionFeeLogGateaway
                .findByStudentIdsAndMonthAndStatus(studentIds, month)
                .orElse(List.of());

        Map<UUID, TuitionFeeModel> feeMap = existingFees.stream()
                .collect(Collectors.toMap(TuitionFeeModel::studentId, fee -> fee));

        List<TuitionFeeModel> allFees = studentIds.stream()
                .map(studentId -> feeMap.getOrDefault(studentId,
                        new TuitionFeeModel(
                                UUID.randomUUID(),
                                studentId,
                                "TUITION",
                                "INCOMING",
                                "PENDING",
                                null,
                                month,
                                200000
                        )))
                .collect(Collectors.toList());

        // Simpan yang belum ada ini gaes
        List<TuitionFeeModel> newPendingFees = allFees.stream()
                .filter(fee -> "PENDING".equals(fee.status()) && fee.transactionDate() == null)
                .filter(fee -> !existingFees.contains(fee))
                .toList();

        if (!newPendingFees.isEmpty()) {
            tuitionFeeLogGateaway.saveAll(newPendingFees);
        }

        return allFees;
    }

    private void ensureTuitionFeesExist(List<UUID> studentIds, String month) {
        logger.info("[tuition use case] - Ensuring tuition fees exist for students: {}", studentIds.toString());

        List<TuitionFeeModel> existing = tuitionFeeLogGateaway
                .findByStudentIdsAndMonthAndStatus(studentIds, month)
                .orElse(List.of());
        logger.info("[tuition use case] - Found {} existing fees", existing.toString());

        Map<UUID, TuitionFeeModel> existingMap = existing.stream()
                .collect(Collectors.toMap(TuitionFeeModel::studentId, fee -> fee));

        List<TuitionFeeModel> newPendingFees = studentIds.stream()
                .filter(id -> !existingMap.containsKey(id))
                .map(id -> new TuitionFeeModel(
                        UUID.randomUUID(),
                        id,
                        "TUITION",
                        "INCOMING",
                        "PENDING",
                        null,
                        month,
                        200000
                ))
                .toList();

        if (!newPendingFees.isEmpty()) {
            logger.info("[tuition use case] - Creating {} new PENDING tuition fees", newPendingFees.toString());
            tuitionFeeLogGateaway.saveAll(newPendingFees);
            logger.info("[tuition use case] - Saved {} new pending tuition records", newPendingFees.toString());
        } else {
            logger.info("[tuition use case] - All students already have tuition fees for month: {}", month);
        }
    }


}


