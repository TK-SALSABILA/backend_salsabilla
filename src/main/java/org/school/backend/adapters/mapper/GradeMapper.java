package org.school.backend.adapters.mapper;

import org.school.backend.application.dto.GradeDto;
import org.school.backend.domain.model.GradeModel;

public interface GradeMapper {

    public static GradeModel toModel(GradeDto record){
        return new GradeModel(
                record.id(),
                record.gradeLevel()
        );
    }
}
