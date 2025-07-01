package org.school.backend.application.dto;

import org.school.backend.application.mappers.GradeMapper;
import org.school.backend.domain.model.GradeModel;
import org.school.backend.domain.model.StudentModel;

import java.io.Serializable;
import java.time.LocalDateTime;

import static org.school.backend.application.utils.DateTimeFormatterConfig.toStringFormat;

public record StudentDetailsDto(
        Integer id,
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
        GradeDto gradeClass
) implements Serializable {
    public StudentDetailsDto(StudentModel entity) {
        this(
                entity.id(),
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
                GradeMapper.toDto(entity.gradeClass())
        );
    }
}
