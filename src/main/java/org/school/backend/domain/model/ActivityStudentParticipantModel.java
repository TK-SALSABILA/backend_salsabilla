package org.school.backend.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public record ActivityStudentParticipantModel(
        UUID id,
        UUID activityId,
        UUID studentId,
        UUID gradeId,
        String studentName,
        String studentNis,
        String gradeName,
        Integer amountRequired,
        Integer amountPaid,
        Integer remainingAmount,
        String paymentStatus,
        LocalDateTime lastPaymentDate
) implements Serializable {

    public ActivityStudentParticipantModel(
            UUID activityId,
            UUID studentId,
            UUID gradeId,
            String studentName,
            String studentNis,
            String gradeName,
            Integer amountRequired,
            Integer amountPaid,
            Integer remainingAmount,
            String paymentStatus
    ){
        this(
                null,
                activityId,
                studentId,
                gradeId,
                studentName,
                studentNis,
                gradeName,
                amountRequired,
                amountPaid,
                remainingAmount,
                paymentStatus,
                null
        );
    }
}
