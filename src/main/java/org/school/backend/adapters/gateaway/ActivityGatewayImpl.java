package org.school.backend.adapters.gateaway;

import org.school.backend.adapters.datasources.ActivityDataSource;
import org.school.backend.adapters.mapper.ActivityMapper;
import org.school.backend.domain.gateaway.ActivityGateway;
import org.school.backend.domain.model.ActivityModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ActivityGatewayImpl implements ActivityGateway {
    private final ActivityDataSource activityDataSource;

    public ActivityGatewayImpl(final ActivityDataSource activityDataSource){
        this.activityDataSource = activityDataSource;
    }

    @Override
    public List<ActivityModel> getActivityStudent(int page, int rpp) {
        return ActivityMapper.toModel(activityDataSource.getActivityStudent(page, rpp));
    }

    @Override
    public Optional<ActivityModel> getActivityDetail(UUID id) {
        return Optional.of(ActivityMapper.convertResponseToModel(activityDataSource.getActivityDetail(id)));
    }

    @Override
    public ActivityModel createActivity(ActivityModel record) {
        return activityDataSource.createActivity(ActivityMapper.convertEntityToModel(record));
    }

    @Override
    public void updateActivity(UUID activityId, int amount) {
        activityDataSource.updateActivity(activityId,amount);
    }
}
