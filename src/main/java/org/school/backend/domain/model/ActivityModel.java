package org.school.backend.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ActivityModel(
        UUID id,
        String activityName,
        LocalDateTime activityDate,
        String description,
        Integer totalFundsRequired,
        Integer totalFundRaised,
        Boolean isActive,
        List<ActivityClassModel> classParticipant
) implements Serializable {
}
