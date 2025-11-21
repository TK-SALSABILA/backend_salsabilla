package org.school.backend.application.dto.request;

import org.school.backend.application.dto.ActivityClassDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


public record ActivityRequestDto(
        String activityName,
        LocalDateTime activityDate,
        String description,
        Integer totalFundsRequired,
        List<ActivityClassDto> classParticipant,
        Boolean isActive
) implements Serializable {
}
