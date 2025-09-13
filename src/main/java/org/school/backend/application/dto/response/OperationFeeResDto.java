package org.school.backend.application.dto.response;

import org.school.backend.domain.model.OperationalFeeModel;

import java.io.Serializable;
import java.util.UUID;

import static org.school.backend.application.utils.DateTimeFormatterConfig.toStringFormat;

public record OperationFeeResDto(
        UUID id,
        UUID studentId,
        String studentName,
        String paymentType,
        Integer totalAmount,
        String transactionDate,
        String description
) implements Serializable {


}
