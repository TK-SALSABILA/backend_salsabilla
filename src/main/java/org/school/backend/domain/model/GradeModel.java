package org.school.backend.domain.model;

import java.io.Serializable;
import java.util.UUID;

public record GradeModel(
        UUID id,
        String gradeLevel
) implements Serializable {
}
