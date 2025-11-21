package org.school.backend.domain.model;

import java.io.Serializable;
import java.util.UUID;

public record ActivityClassParticipantModel(
        UUID id,
        UUID activityId,
        UUID gradeId,
        String gradeName
) implements Serializable {

    public ActivityClassParticipantModel(
            UUID activityId,
            UUID gradeId,
            String gradeName
    ){
        this(null,activityId,gradeId,gradeName);
    }
}
