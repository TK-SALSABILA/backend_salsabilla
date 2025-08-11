package org.school.backend.application.dto.request;

import org.school.backend.domain.model.TuitionFeeModel;

import java.io.Serializable;
import java.util.UUID;

import static org.school.backend.application.utils.DateTimeFormatterConfig.toStringFormat;

public record TuitonFeeReqDto(
        UUID studentId,
        String paymentType,
        String transactionType,
        String transactionDate,
        Integer amount,
        String month
) implements Serializable {

    public TuitonFeeReqDto(TuitionFeeModel entity){
        this(
                entity.studentId(),
                entity.paymentType(),
                entity.transactionType(),
                toStringFormat(entity.transactionDate()),
                entity.amount(),
                entity.month()
        );
    }
}
