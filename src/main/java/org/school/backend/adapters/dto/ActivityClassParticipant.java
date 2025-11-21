package org.school.backend.adapters.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityClassParticipant {
    UUID activityId;
    UUID gradeId;
    String gradeName;
}
