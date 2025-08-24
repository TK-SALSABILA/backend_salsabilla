package org.school.backend.application.dto.request;

import org.school.backend.application.dto.BaseParamsDto;

import java.util.UUID;

public record TuitionParamDto(
        int page,
        int rpp,
        String q,
        String status,
        String month,
        UUID classId
) implements BaseParamsDto {
}
