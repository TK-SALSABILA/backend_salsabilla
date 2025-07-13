package org.school.backend.application.mappers;

import org.school.backend.application.dto.GradeDto;
import org.school.backend.application.dto.StudentGradeDto;
import org.school.backend.domain.model.GradeModel;
import org.school.backend.domain.model.StudentGradeModel;

public class GradeMapper {
    public static GradeDto toDto(GradeModel entity){
        return new GradeDto(entity);
    }
    public static GradeModel toGradeModel(GradeDto record){
        return new GradeModel(
                record.id(),
                record.gradeLevel()
        );
    }
}
