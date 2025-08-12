package org.school.backend.application.usecases.impl;

import org.school.backend.application.dto.request.TuitonFeeReqDto;
import org.school.backend.application.dto.response.StudentsLogsOutputDto;
import org.school.backend.application.dto.response.TuitonFeeResDto;
import org.school.backend.application.usecases.TuitionFeeUseCase;
import org.school.backend.domain.gateaway.GradeStudentLogGateaway;
import org.school.backend.domain.gateaway.SavingLogGateaway;
import org.school.backend.domain.gateaway.StudentLogGateaway;
import org.school.backend.domain.gateaway.TuitionFeeLogGateaway;
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


    public TuitonFeeUseCaseImpl(final TuitionFeeLogGateaway tuitionFeeLogGateaway, final GradeStudentLogGateaway gradeStudentLogGateaway, final StudentLogGateaway studentLogGateaway,final SavingLogGateaway savingLogGateaway){
        this.tuitionFeeLogGateaway = tuitionFeeLogGateaway;
        this.gradeStudentLogGateaway = gradeStudentLogGateaway;
        this.studentLogGateaway = studentLogGateaway;
        this.savingLogGateaway = savingLogGateaway;
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
    }

    @Override
    public void createTuition( TuitonFeeReqDto record) {
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
        tuitionFeeLogGateaway.createTuition(tuitionModel);
    }

    private List<UUID> getStudentIdsByClass(Object classId) {
        return gradeStudentLogGateaway.findStudentByClass(classId)
                .studentId();
    }

    private Map<UUID, StudentsLogsOutputDto> buildStudentMap(List<UUID> studentIds) {
        return studentIds.stream()
                .map(this::findStudentOrThrow)
                .collect(Collectors.toMap(StudentModel::id, StudentsLogsOutputDto::new));
    }

    private StudentModel findStudentOrThrow(UUID id) {
        return studentLogGateaway.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found: " + id));
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


}


