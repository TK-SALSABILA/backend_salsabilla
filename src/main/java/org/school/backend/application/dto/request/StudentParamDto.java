package org.school.backend.application.dto.request;

import java.io.Serializable;

public record StudentParamDto(
        int page,
        int rpp,
        String q
) implements Serializable {

    public boolean hasKeyword() {
        return q != null && !q.isBlank();
    }
}
