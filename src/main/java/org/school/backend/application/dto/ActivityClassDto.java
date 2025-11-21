package org.school.backend.application.dto;

import java.io.Serializable;
import java.util.UUID;

public record ActivityClassDto(
        UUID gradeId,
        String gradeName
) implements Serializable {
}
