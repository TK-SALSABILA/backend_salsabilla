package org.school.backend.application.dto.request;

import java.io.Serializable;
import java.util.UUID;

public record OperationFeeReqDto(
        UUID studentId,
        String paymentType,
        String transactionType,
        String transactionDate,
        Integer amount,
        String description
) implements Serializable {
}
