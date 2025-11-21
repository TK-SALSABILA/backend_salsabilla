package org.school.backend.application.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public record ActivityStudentParticipantResponseDto(
        UUID id,
        UUID studentId,
        UUID gradeId,
        String studentName,
        String gradeName,
        Integer amountRequired,
        Integer amountPaid,
        Integer remainingAmount,
        String paymentStatus,
        String lastPaymentDate
) implements Serializable {
}
