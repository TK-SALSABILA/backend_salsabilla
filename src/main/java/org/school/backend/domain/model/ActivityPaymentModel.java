package org.school.backend.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public record ActivityPaymentModel(
        UUID id,
        UUID activityId,
        UUID studentId,
        Integer amount,
        String paymentType,
        LocalDateTime paymentDate,
        String description,
        LocalDateTime createdAt
) implements Serializable {

    public ActivityPaymentModel(
            UUID activityId,
            UUID studentId,
            Integer amount,
            String paymentType,
            LocalDateTime paymentDate,
            String description
    ){
        this(
                null,
                activityId,
                studentId,
                amount,
                paymentType,
                paymentDate,
                description,
                null
        );
    }
}
