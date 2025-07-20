package org.school.backend.adapters.mapper;

import org.school.backend.application.dto.StudentGradeDto;
import org.school.backend.domain.model.StudentGradeModel;

public class StudentGradeMapper {
    public static StudentGradeModel toModel(StudentGradeDto entities){
        return new StudentGradeModel(
                entities.academicYear(),
                entities.isCurrent(),
                GradeMapper.toModel(entities.gradeLog())
        );
    }
}
