package org.school.backend.adapters.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequest {
    String activityName;
    LocalDateTime activityDate;
    String description;
    Integer totalFundsRequired;
    Boolean isActive;
}
