package org.school.backend.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public record OperationalFeeModel(
        UUID id,
        UUID studentId,
        String paymentType,
        String transactionType,
        String description,
        String status,
        LocalDateTime transactionDate,
        Integer amount
) implements Serializable {



    public OperationalFeeModel(
            UUID studentId,
            String paymentType,
            String transactionType,
            String description,
            String status,
            LocalDateTime transactionDate,
            Integer amount
    ){
        this(null, studentId, paymentType, transactionType, description,status, transactionDate, amount);
    }

}
