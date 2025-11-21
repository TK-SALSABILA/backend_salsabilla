package org.school.backend.domain.gateaway;

import org.school.backend.domain.model.ActivityModel;

import java.util.List;
import java.util.UUID;

public interface ActivityGateway {
    List<ActivityModel> getActivityStudent(int page, int rpp);
    ActivityModel createActivity(ActivityModel record);
    void updateActivity(UUID activityId, ActivityModel record);
}
