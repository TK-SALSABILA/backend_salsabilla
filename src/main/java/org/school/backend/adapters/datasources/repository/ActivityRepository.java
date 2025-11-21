package org.school.backend.adapters.datasources.repository;

import org.school.backend.adapters.dto.ActivityRequest;
import org.school.backend.adapters.dto.ActivityResponse;
import org.school.backend.domain.model.ActivityModel;

import java.util.List;

public interface ActivityRepository {
    List<ActivityResponse> getActivityStudent(int page, int rpp);
    ActivityModel createActivity(ActivityRequest request);
}
