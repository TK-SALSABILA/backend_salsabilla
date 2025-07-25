package org.school.backend.application.dto.response;

import org.school.backend.domain.model.SubjectModel;

import java.io.Serializable;
import java.util.UUID;

public record SubjectLogsDto(
        UUID id,
        String subjectName,
        String subjectCode,
        String gradeLevel,
        Boolean isMandatory,
        String description
) implements Serializable {

    public SubjectLogsDto(SubjectModel entity){
        this(
                entity.id(),
                entity.subjectName(),
                entity.subjectCode(),
                entity.gradeLevel(),
                entity.isMandatory(),
                entity.description()
        );
    }
}
