package org.school.backend.adapters.datasources.impl;

import org.school.backend.adapters.datasources.ActivityDataSource;
import org.school.backend.adapters.datasources.repository.ActivityRepository;
import org.school.backend.adapters.dto.ActivityRequest;
import org.school.backend.adapters.dto.ActivityResponse;
import org.school.backend.domain.model.ActivityModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ActivityDataSourceImpl implements ActivityDataSource {
    private ActivityRepository activityRepository;

    public ActivityDataSourceImpl(final ActivityRepository activityRepository){
        this.activityRepository = activityRepository;
    }
    @Override
    public List<ActivityResponse> getActivityStudent(int page, int rpp) {
        return activityRepository.getActivityStudent(page,rpp);
    }

    @Override
    public ActivityModel createActivity(ActivityRequest request) {
       return   activityRepository.createActivity(request);
    }

    @Override
    public void updateActivity(UUID activityId, int amount) {
        activityRepository.updateActivity(activityId,amount);
    }
}
