package org.school.backend.application.dto.request;

import java.io.Serializable;
import java.util.UUID;

public record StudentParamDto(
        int page,
        int rpp,
        String q,
        UUID classId
) implements Serializable {
    public boolean hasAnyFilter() {
        return q() != null || classId != null;
    }
}
