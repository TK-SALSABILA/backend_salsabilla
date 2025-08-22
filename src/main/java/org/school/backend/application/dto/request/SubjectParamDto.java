package org.school.backend.application.dto.request;

import org.school.backend.application.dto.BaseParamsDto;

import java.io.Serializable;

public record SubjectParamDto(
        int page,
        int rpp,
        String q,
        Boolean isMandatory
) implements BaseParamsDto {
     public boolean hasMandatory() {
        return isMandatory() != null;
    }
}
