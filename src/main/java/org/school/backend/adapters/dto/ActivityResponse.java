package org.school.backend.adapters.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponse {
    UUID id;
    String activityName;
    Boolean isActive;
    LocalDateTime activityDate;
    Integer totalFundsRequired;
    Integer totalFundRaised;
    List<ActivityClass> classParticipant;
    String description;
}
