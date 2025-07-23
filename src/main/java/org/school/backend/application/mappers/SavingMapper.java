package org.school.backend.application.mappers;

import org.school.backend.application.dto.response.SavingLogOutputDto;
import org.school.backend.domain.model.SavingModel;
import org.school.backend.domain.model.StudentModel;

import static org.school.backend.application.utils.DateTimeFormatterConfig.toStringFormat;

public class SavingMapper {
    public static SavingLogOutputDto toDto(SavingModel saving, StudentModel student) {
        return new SavingLogOutputDto(
                saving.id(),
                saving.studentId(),
                student.fullName(),
                saving.paymentType(),
                saving.amount(),
                toStringFormat(saving.transactionDate()),
                saving.description()
        );
    }
}
