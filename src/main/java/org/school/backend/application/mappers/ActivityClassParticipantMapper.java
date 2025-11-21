package org.school.backend.application.mappers;

import org.school.backend.application.dto.ActivityClassDto;
import org.school.backend.domain.model.ActivityClassModel;

import java.util.ArrayList;
import java.util.List;

public class ActivityClassParticipantMapper {
    public static List<ActivityClassDto> toDto(List<ActivityClassModel> entities){
        List<ActivityClassDto> activityClassDtos = new ArrayList<>();
        entities.forEach((entity) -> {
            ActivityClassDto activityClassDto = new ActivityClassDto(
                    entity.gradeId(), entity.gradeName()
            );
            activityClassDtos.add(activityClassDto);
        });

        return activityClassDtos;
    }

    public static List<ActivityClassModel> convertDtoToModel(List<ActivityClassDto> entities){
        List<ActivityClassModel> activityClassDtos = new ArrayList<>();
        entities.forEach((entity) -> {
            ActivityClassModel activityClassDto = new ActivityClassModel(
                    entity.gradeId(), entity.gradeName()
            );
            activityClassDtos.add(activityClassDto);
        });

        return activityClassDtos;
    }

}
