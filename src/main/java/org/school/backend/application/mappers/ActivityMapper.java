package org.school.backend.application.mappers;

import org.school.backend.application.dto.request.ActivityRequestDto;
import org.school.backend.application.dto.response.ActivityResponseDto;
import org.school.backend.domain.model.ActivityModel;

import java.util.ArrayList;
import java.util.List;

import static org.school.backend.application.utils.DateTimeFormatterConfig.toStringFormat;

public class ActivityMapper {

    public static ActivityModel convertEntityToModel(ActivityRequestDto request){
        return new ActivityModel(
                null,
                request.activityName(),
                request.activityDate(),
                request.description(),
                request.totalFundsRequired(),
                0,
                true,
                ActivityClassParticipantMapper.convertDtoToModel(request.classParticipant())

        );
    }
    public static List<ActivityResponseDto> toListDto(List<ActivityModel> entities){
        List<ActivityResponseDto> activityResponseDatas = new ArrayList<>();
        entities.forEach((entity) -> activityResponseDatas.add(new ActivityResponseDto(
                entity.id(),
                entity.activityName(),
                entity.isActive(),
                toStringFormat(entity.activityDate()),
                entity.totalFundsRequired(),
                entity.totalFundsRequired(),
                ActivityClassParticipantMapper.toDto(entity.classParticipant()),
                entity.description()

        )));
        return activityResponseDatas;
    }
}
