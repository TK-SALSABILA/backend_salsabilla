package org.school.backend.application.dto.response;

import org.school.backend.application.dto.ActivityClassDto;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public record ActivityResponseDto(
        UUID id,
        String activityName,
        Boolean status,
        String activityDate,
        Integer totalFundsRequired,
        Integer totalFundRaised,
        List<ActivityClassDto> classParticipant,
        String description

) implements Serializable {
}
