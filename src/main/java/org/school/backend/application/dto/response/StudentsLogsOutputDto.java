package org.school.backend.application.dto.response;

import org.school.backend.domain.model.StudentModel;

import java.io.Serializable;
import java.time.LocalDateTime;

public record StudentsLogsOutputDto(
        Integer id,
        String fullName,
        String nickName,
        String nik,
        String gender,
        LocalDateTime dateBirth,
        String birthOrder
) implements Serializable {

    public StudentsLogsOutputDto(StudentModel studentRecordModel){
        this(
                studentRecordModel.id(),
                studentRecordModel.fullName(),
                studentRecordModel.nickName(),
                studentRecordModel.nik(),
                studentRecordModel.gender(),
                studentRecordModel.dateBirth(),
                studentRecordModel.birthOrder()
        );
    }
}
