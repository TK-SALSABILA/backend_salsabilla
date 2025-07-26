package org.school.backend.adapters.mapper;

import org.school.backend.adapters.dto.StudentGrade;
import org.school.backend.domain.model.StudentGradeModel;

public class StudentGradeMapper {

    static StudentGrade toDto(StudentGradeModel entities){
        return new StudentGrade(
                entities.academicYear(),
                entities.isCurrent(),
                GradeMapper.convertEntityToModel(entities.gradeLog())
        );
    }

    static StudentGradeModel convertModelToEntity(StudentGrade model){
        return new StudentGradeModel(model.getAcademicYear(),model.getIsCurrent(),GradeMapper.convertModelToEntity(model.getGradeLog()));
    }

}
