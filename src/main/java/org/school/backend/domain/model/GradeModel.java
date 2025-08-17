package org.school.backend.domain.model;

import java.io.Serializable;
import java.util.UUID;

public record GradeModel(
        UUID id,
        String gradeLevel
) implements Serializable {

    public GradeModel(UUID id, String gradeLevel){
        this.id = id;
        this.gradeLevel = gradeLevel;
    }
}
