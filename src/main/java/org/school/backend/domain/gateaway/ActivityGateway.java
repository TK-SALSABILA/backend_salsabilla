package org.school.backend.domain.gateaway;

import org.school.backend.domain.model.ActivityModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActivityGateway {
    List<ActivityModel> getActivityStudent(int page, int rpp);
    Optional<ActivityModel> getActivityDetail(UUID id);
    ActivityModel createActivity(ActivityModel record);
    void updateActivity(UUID activityId, int amount);
}
