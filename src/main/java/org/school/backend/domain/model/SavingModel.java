package org.school.backend.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public record SavingModel(
        UUID id,
        UUID studentId,
        UUID activityId,
        String paymentType,
        String transactionType,
        LocalDateTime transactionDate,
        Integer amount,
        String description
) implements Serializable {

    public SavingModel(
            UUID studentId,
            UUID activityId,
            String paymentType,
            String transactionType,
            LocalDateTime transactionDate,
            Integer amount,
            String description
    ){
        this(
                null,
                activityId,
                studentId,
                paymentType,
                transactionType,
                transactionDate,
                amount,
                description
        );
    }
}
