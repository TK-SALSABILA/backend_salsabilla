package org.school.backend.application.dto.request;

import org.school.backend.application.dto.BaseParamsDto;

import java.util.UUID;

public record SavingParamDto(
        int page,
        int rpp,
        String q,
        String status,
        String month,
        UUID classId
) implements BaseParamsDto {

    public Boolean hasParams(){
        return q != null || status != null || classId != null;

    }
}
