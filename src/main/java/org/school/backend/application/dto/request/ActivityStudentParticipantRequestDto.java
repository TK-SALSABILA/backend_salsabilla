package org.school.backend.application.dto.request;

import java.io.Serializable;
import java.util.UUID;

public record ActivityStudentParticipantRequestDto(
        UUID id,
        UUID activityId,
        UUID studentId,
        UUID gradeId,
        String studentName,
        String gradeName,
        String studentNis,
        Integer amountRequired,
        Integer amountPaid,
        Integer remainingAmount,
        String paymentStatus,
        String lastPaymentDate
) implements Serializable {
}
