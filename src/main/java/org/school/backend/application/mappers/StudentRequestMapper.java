package org.school.backend.application.mappers;

import org.school.backend.application.dto.request.StudentRequestDto;
import org.school.backend.domain.model.StudentModel;

import static org.school.backend.application.utils.DateTimeFormatterConfig.parseDate;

public class StudentRequestMapper {
    public static StudentModel toModel(StudentRequestDto dto) {
        return new StudentModel(
                null,
                dto.fullName(),
                dto.nickName(),
                dto.nik(),
                dto.gender(),
                parseDate(dto.dateBirth()),
                dto.birthOrder(),
                dto.tribe(),
                dto.address(),
                dto.height(),
                dto.weight(),
                StudentGradeMapper.toGradeModel(dto.gradeClass()),
                ParentMapper.toModel(dto.parent())
        );
    }
}
