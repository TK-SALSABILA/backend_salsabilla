package org.school.backend.application.mappers;

import org.school.backend.application.dto.response.OperationFeeResDto;
import org.school.backend.domain.model.OperationalFeeModel;
import org.school.backend.domain.model.StudentModel;

import static org.school.backend.application.utils.DateTimeFormatterConfig.toStringFormat;

public class OptfeeMapper {

    public static OperationFeeResDto toDto(OperationalFeeModel entities, StudentModel student){
        return new OperationFeeResDto(
                entities.id(),
                entities.studentId(),
                student.fullName(),
                entities.paymentType(),
                entities.amount(),
                toStringFormat(entities.transactionDate()),
                entities.description()
        );
    }
}
