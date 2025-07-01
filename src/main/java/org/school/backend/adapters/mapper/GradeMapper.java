package org.school.backend.adapters.mapper;

import org.school.backend.application.dto.GradeDto;
import org.school.backend.domain.model.GradeModel;

public class GradeMapper {
    public static GradeModel toModel(GradeDto entities){
        return new GradeModel(
                entities.gradeLevel(),
                entities.academicYear()
        );
    }
}
