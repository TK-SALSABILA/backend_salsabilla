package org.school.backend.application.dto;

import org.school.backend.domain.model.GradeModel;

import java.util.UUID;

public record GradeDto(
        UUID id,
        String gradeLevel
) {

    public GradeDto(GradeModel entities){
        this(
                entities.id(),
                entities.gradeLevel()
        );
    }
}
