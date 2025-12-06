package org.school.backend.application.dto.request;

import java.io.Serializable;
import java.util.UUID;

public record ActivityPaymentRequest(
        UUID studentId,
        UUID activityId,
        String paymentType,
        String transactionType,
        Integer amount,
        String description,
        String transactionDate
) implements Serializable {
}
