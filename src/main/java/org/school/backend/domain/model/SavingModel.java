package org.school.backend.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public record SavingModel(
        UUID id,
        UUID studentId,
        String paymentType,
        String transactionType,
        LocalDateTime transactionDate,
        Integer amount,
        String description
) implements Serializable {

    public SavingModel(
            UUID studentId,
            String paymentType,
            String transactionType,
            LocalDateTime transactionDate,
            Integer amount,
            String description
    ){
        this(
                null,
                studentId,
                paymentType,
                transactionType,
                transactionDate,
                amount,
                description
        );
    }
}
