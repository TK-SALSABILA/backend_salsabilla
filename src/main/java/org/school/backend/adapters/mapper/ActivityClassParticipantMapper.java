package org.school.backend.adapters.mapper;

import org.school.backend.adapters.dto.ActivityClassParticipant;
import org.school.backend.domain.model.ActivityClassParticipantModel;

import java.util.ArrayList;
import java.util.List;

public interface ActivityClassParticipantMapper {

    static List<ActivityClassParticipant> toDto(List<ActivityClassParticipantModel> entities){
        List<ActivityClassParticipant> resultData = new ArrayList<>();
        entities.forEach((entity) -> resultData.add(new ActivityClassParticipant(
                entity.activityId(),
                entity.gradeId(),
                entity.gradeName()
        )));
        return resultData;
    }
}
