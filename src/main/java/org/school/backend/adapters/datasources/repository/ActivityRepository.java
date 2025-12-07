package org.school.backend.adapters.datasources.repository;

import org.school.backend.adapters.dto.ActivityRequest;
import org.school.backend.adapters.dto.ActivityResponse;
import org.school.backend.domain.model.ActivityModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActivityRepository {
    List<ActivityResponse> getActivityStudent(int page, int rpp);
    Optional<ActivityResponse> getActivityDetail(UUID id);
    ActivityModel createActivity(ActivityRequest request);
    void updateActivity(UUID activityId,int amount);
}
