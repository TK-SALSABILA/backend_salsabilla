package org.school.backend.application.usecases.impl;

import org.school.backend.application.dto.request.ActivityPaymentRequest;
import org.school.backend.application.dto.request.ActivityStudentParticipantRequestDto;
import org.school.backend.application.dto.response.ActivityStudentParticipantResponseDto;
import org.school.backend.application.exception.InvalidPaymentException;
import org.school.backend.application.mappers.ActivityStudentParticipantMapper;
import org.school.backend.application.usecases.ActivityStudentParticipantUseCase;
import org.school.backend.domain.gateaway.ActivityGateway;
import org.school.backend.domain.gateaway.ActivityPaymentGateway;
import org.school.backend.domain.gateaway.ActivityStudentParticipantGateway;
import org.school.backend.domain.gateaway.SavingLogGateaway;
import org.school.backend.domain.model.ActivityPaymentModel;
import org.school.backend.domain.model.ActivityStudentParticipantModel;
import org.school.backend.domain.model.SavingModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.school.backend.application.utils.DateTimeFormatterConfig.parseDate;


public class ActivityStudentParticipantUseCaseImpl implements ActivityStudentParticipantUseCase {

    private final ActivityStudentParticipantGateway activityStudentParticipantGateway;
    private final SavingLogGateaway savingLogGateway;
    private final ActivityPaymentGateway activityPaymentGateway;
    private final ActivityGateway activityGateway;

    public ActivityStudentParticipantUseCaseImpl(
            ActivityStudentParticipantGateway activityStudentParticipantGateway,
            SavingLogGateaway savingLogGateway,
            ActivityPaymentGateway activityPaymentGateway,
            ActivityGateway activityGateway

    ) {
        this.activityStudentParticipantGateway = activityStudentParticipantGateway;
        this.savingLogGateway = savingLogGateway;
        this.activityPaymentGateway = activityPaymentGateway;
        this.activityGateway = activityGateway;
    }

    @Override
    public List<ActivityStudentParticipantResponseDto> getListStudent(UUID activityId, int page, int rpp) {
        return ActivityStudentParticipantMapper.toDto(
                activityStudentParticipantGateway.getActivityStudent(activityId, page, rpp)
        );
    }

    @Override
    public void createActivityStudent(List<ActivityStudentParticipantRequestDto> record) {
        activityStudentParticipantGateway.createActivityStudents(
                ActivityStudentParticipantMapper.convertModelsToEntities(record)
        );
    }

    @Override
    public void createPaymentStudent(ActivityPaymentRequest request) {

        ActivityStudentParticipantModel student = activityStudentParticipantGateway
                .getStudentById(request.activityId(), request.studentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found in this activity"));


        if (request.amount() > student.remainingAmount()) {
            throw new InvalidPaymentException("Payment amount (" + request.amount() +
                    ") exceeds remaining amount (" + student.remainingAmount() + ")");
        }

        if ("TABUNGAN".equals(request.paymentType())) {
            Integer saldo = savingLogGateway.reduction(request.studentId());

            if (saldo < request.amount()) {
                throw new InvalidPaymentException("Insufficient balance. Saldo: " + saldo + ", Payment: " + request.amount());
            }

            SavingModel savingRecord = new SavingModel(
                    request.studentId(),
                    request.activityId(),
                    request.paymentType(),
                    "WITHDRAW",
                    LocalDateTime.now(),
                    -request.amount(),
                    request.description()
            );
            savingLogGateway.create(savingRecord);
        }

        ActivityPaymentModel dataPayment = new ActivityPaymentModel(
                request.activityId(),
                request.studentId(),
                request.amount(),
                request.paymentType(),
                parseDate(request.transactionDate()),
                request.description()
        );
        activityPaymentGateway.createPayment(dataPayment);

        activityStudentParticipantGateway.updateActivityStudents(
                request.activityId(),
                request.studentId(),
                request.amount()
        );

        activityGateway.updateActivity(
                request.activityId(),
                request.amount()
        );
    }
}

