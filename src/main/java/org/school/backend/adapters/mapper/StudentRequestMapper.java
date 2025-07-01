package org.school.backend.adapters.mapper;

import org.school.backend.application.dto.request.StudentRequestDto;
import org.school.backend.domain.model.StudentModel;

import static org.school.backend.application.utils.DateTimeFormatterConfig.parseDate;

public class StudentRequestMapper {
    public static StudentModel toModel(StudentRequestDto request){
        return new StudentModel(
               null,
                request.fullName(),
                request.nickName(),
                request.nik(),
                request.gender(),
                parseDate(request.dateBirth()),
                request.birthOrder(),
                request.tribe(),
                request.address(),
                request.height(),
                request.weight(),
                GradeMapper.toModel(request.gradeClass()),
                ParentMapper.toModel(request.parent())
        );
    }
}
