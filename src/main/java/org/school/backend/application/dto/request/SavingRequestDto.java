package org.school.backend.application.dto.request;

import org.school.backend.application.dto.StudentDetailsDto;
import org.school.backend.domain.model.SavingModel;

import java.io.Serializable;
import java.util.UUID;

import static org.school.backend.application.utils.DateTimeFormatterConfig.toStringFormat;

public record SavingRequestDto(
        UUID studentId,
        String paymentType,
        String transactionType,
        Integer amount,
        String description,
        String transactionDate

) implements Serializable {

    public SavingRequestDto(SavingModel entity){
        this(
                entity.studentId(),
                entity.paymentType(),
                entity.transactionType(),
                entity.amount(),
                entity.description(),
                toStringFormat(entity.transactionDate())
        );
    }
}
