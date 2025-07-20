package org.school.backend.application.dto.response;

import org.school.backend.domain.model.StudentModel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.school.backend.application.utils.DateTimeFormatterConfig.toStringFormat;

public record StudentsLogsOutputDto(
        UUID id,
        String fullName,
        String nickName,
        String nik,
        String gender,
        String dateBirth,
        String birthOrder
) implements Serializable {

    public StudentsLogsOutputDto(StudentModel studentRecordModel){
        this(
                studentRecordModel.id(),
                studentRecordModel.fullName(),
                studentRecordModel.nickName(),
                studentRecordModel.nik(),
                studentRecordModel.gender(),
                toStringFormat(studentRecordModel.dateBirth()),
                studentRecordModel.birthOrder()
        );
    }
}
