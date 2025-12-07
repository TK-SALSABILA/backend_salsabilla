package org.school.backend.adapters.mapper;

import org.school.backend.adapters.dto.ActivityClass;
import org.school.backend.adapters.dto.ActivityRequest;
import org.school.backend.adapters.dto.ActivityResponse;
import org.school.backend.domain.model.ActivityModel;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;


public interface ActivityMapper {
    static ActivityResponse toActivityResponseSingle(List<Map<String, Object>> rawData) {
        if (rawData.isEmpty()) {
            return null;
        }

        Map<String, Object> first = rawData.get(0);

        return ActivityResponse.builder()
                .id(UUID.fromString(first.get("id").toString()))
                .activityName((String) first.get("activity_name"))
                .isActive((Boolean) first.get("is_active"))
                .activityDate(((Timestamp) first.get("activity_date")).toLocalDateTime())
                .totalFundsRequired((Integer) first.get("total_fund_required"))
                .totalFundRaised((Integer) first.get("total_fund_raised"))
                .classParticipant(toParticipants(rawData))
                .description((String) first.get("description"))
                .build();
    }
      static List<ActivityResponse> toActivityResponses(List<Map<String, Object>> rawData) {
        return rawData.stream()
                .collect(Collectors.groupingBy(row -> UUID.fromString(row.get("id").toString())))
                .entrySet()
                .stream()
                .map(ActivityMapper::toActivityResponse)
                .toList();
      }

    private static ActivityResponse toActivityResponse(Map.Entry<UUID, List<Map<String, Object>>> entry) {
        Map<String, Object> first = entry.getValue().get(0);

        return ActivityResponse.builder()
                .id(UUID.fromString(first.get("id").toString()))
                .activityName((String) first.get("activity_name"))
                .isActive((Boolean) first.get("is_active"))
                .activityDate(((Timestamp) first.get("activity_date")).toLocalDateTime())
                .totalFundsRequired((Integer) first.get("total_fund_required"))
                .totalFundRaised((Integer) first.get("total_fund_raised"))
                .classParticipant(toParticipants(entry.getValue()))
                .description((String) first.get("description"))
                .build();
    }

    private static List<ActivityClass> toParticipants(List<Map<String, Object>> rows) {
        return rows.stream()
                .filter(row -> row.get("grade_id") != null)
                .map(row -> new ActivityClass(
                        UUID.fromString(row.get("grade_id").toString()),
                        (String) row.get("grade_name")
                ))
                .distinct()
                .toList();
    }

        static List<ActivityModel> toModel(final List<ActivityResponse> record){
             List<ActivityModel> activityModels = new ArrayList<>();
             record.forEach((entity) -> activityModels.add(new ActivityModel(
                     entity.getId(),
                     entity.getActivityName(),
                     entity.getActivityDate(),
                     entity.getDescription(),
                     entity.getTotalFundsRequired(),
                     entity.getTotalFundRaised(),
                     entity.getIsActive(),
                     ActivityClassMapper.toModel(entity.getClassParticipant())
             )));
             return activityModels;
        }



    static ActivityRequest convertEntityToModel(ActivityModel request){
          return ActivityRequest.builder()
                  .activityName(request.activityName())
                  .activityDate(request.activityDate())
                  .description(request.description())
                  .totalFundsRequired(request.totalFundsRequired())
                  .isActive(request.isActive())
                  .build();
    }

    static ActivityModel convertResponseToModel(Optional<ActivityResponse>  response){
        return new ActivityModel(
                response.get().getId(),
                response.get().getActivityName(),
                response.get().getActivityDate(),
                response.get().getDescription(),
                response.get().getTotalFundsRequired(),
                response.get().getTotalFundRaised(),
                response.get().getIsActive(),
                ActivityClassMapper.toModel(response.get().getClassParticipant())
        );
    }
}
