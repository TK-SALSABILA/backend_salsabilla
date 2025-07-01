package org.school.backend.application.dto.request;

import org.school.backend.application.dto.GradeDto;
import org.school.backend.application.dto.ParentDto;
import org.school.backend.application.mappers.GradeMapper;
import org.school.backend.application.mappers.ParentMapper;
import org.school.backend.domain.model.StudentModel;

import java.io.Serializable;

import static org.school.backend.application.utils.DateTimeFormatterConfig.toStringFormat;

public record StudentRequestDto(
        String fullName,
        String nickName,
        String nik,
        String gender,
        String dateBirth,
        String birthOrder,
        String tribe,
        String address,
        String height,
        String weight,
        GradeDto gradeClass,
        ParentDto parent
) implements Serializable {

    public StudentRequestDto(StudentModel entity){
        this(
                entity.fullName(),
                entity.nickName(),
                entity.nik(),
                entity.gender(),
                toStringFormat(entity.dateBirth()),
                entity.birthOrder(),
                entity.tribe(),
                entity.address(),
                entity.height(),
                entity.weight(),
                GradeMapper.toDto( entity.gradeClass()),
                ParentMapper.toDto(entity.parent())
        );
    }
}
