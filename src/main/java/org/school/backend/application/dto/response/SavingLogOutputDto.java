package org.school.backend.application.dto.response;

import org.school.backend.application.dto.StudentDetailsDto;
import org.school.backend.domain.model.SavingModel;

import java.io.Serializable;
import java.util.UUID;

public record SavingLogOutputDto(
        UUID id,
        UUID studentId,
        String studentName,
        String paymentType,
        Integer totalAmount,
        String transactionDate,
        String description
) implements Serializable {

}
