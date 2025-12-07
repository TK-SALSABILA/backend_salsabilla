package org.school.backend.application.mappers;

import org.school.backend.application.dto.request.ActivityRequestDto;
import org.school.backend.application.dto.response.ActivityResponseDto;
import org.school.backend.domain.model.ActivityModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                entity.totalFundRaised(),
                ActivityClassParticipantMapper.toDto(entity.classParticipant()),
                entity.description()

        )));
        return activityResponseDatas;
    }

    public static ActivityResponseDto convertModelToResponse(Optional<ActivityModel> response){
        return new ActivityResponseDto(
                response.get().id(),
                response.get().activityName(),
                response.get().isActive(),
                toStringFormat(response.get().activityDate()),
                response.get().totalFundsRequired(),
                response.get().totalFundRaised(),
                ActivityClassParticipantMapper.toDto(response.get().classParticipant()),
                response.get().description()
        );
    }
}
