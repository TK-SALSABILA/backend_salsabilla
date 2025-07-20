package org.school.backend.application.dto;

import org.school.backend.application.mappers.StudentGradeMapper;
import org.school.backend.domain.model.StudentModel;

import java.io.Serializable;
import java.util.UUID;

import static org.school.backend.application.utils.DateTimeFormatterConfig.toStringFormat;

public record StudentDetailsDto(
        UUID id,
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
        StudentGradeDto gradeClass
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
                StudentGradeMapper.toDto(entity.gradeClass())
        );
    }
}
