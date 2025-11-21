package org.school.backend.domain.model;

import java.io.Serializable;
import java.util.UUID;

public record StudentParticipantModel(
        UUID studentId,
        String studentName,
        String studentNik,
        UUID gradeId,
        String gradeName
) implements Serializable {
}
