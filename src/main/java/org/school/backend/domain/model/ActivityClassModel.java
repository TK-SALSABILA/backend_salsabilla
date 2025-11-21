package org.school.backend.domain.model;

import java.io.Serializable;
import java.util.UUID;

public record ActivityClassModel(
        UUID gradeId,
        String gradeName
) implements Serializable {}
