package org.school.backend.application.dto.response;

import org.school.backend.domain.model.StudentModel;
import org.school.backend.domain.model.TuitionFeeModel;

import java.io.Serializable;
import java.util.UUID;

import static org.school.backend.application.utils.DateTimeFormatterConfig.toStringFormat;

public record TuitonFeeResDto(
        UUID id,
        String month,
        Integer amount,
        String status,
        String paymentDate,
        StudentsLogsOutputDto student
) implements Serializable {
}
