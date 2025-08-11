package org.school.backend.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.UUID;

public record TuitionFeeModel(
        UUID id,
        UUID studentId,
        String paymentType,
        String transactionType,
        String status,
        LocalDateTime transactionDate,
        String month,
        Integer amount
) implements Serializable {
    public TuitionFeeModel(
            UUID studentId,
            String paymentType,
            String transactionType,
            String status,
            LocalDateTime transactionDate,
            String month,
            Integer amount
    ) {
        this(null, studentId, paymentType, transactionType, status, transactionDate, month, amount);
    }

    public TuitionFeeModel(
            UUID id,
            UUID studentId,
            String paymentType,
            String transactionType,
            String status,
            LocalDateTime transactionDate,
            String month,
            Integer amount
    ) {
        this.id = id;
        this.studentId = studentId;
        this.paymentType = paymentType;
        this.transactionType = transactionType;
        this.status = status;
        this.transactionDate = transactionDate;
        this.month = month;
        this.amount = amount;
    }
}
