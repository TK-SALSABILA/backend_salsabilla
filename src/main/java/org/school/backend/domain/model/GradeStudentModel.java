package org.school.backend.domain.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public record GradeStudentModel(
        UUID classId,
        List<UUID> studentId
) implements Serializable {
}
