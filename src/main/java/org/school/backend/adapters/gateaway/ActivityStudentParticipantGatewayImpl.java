package org.school.backend.adapters.gateaway;

import org.school.backend.adapters.datasources.ActivityStudentParticipantDataSource;
import org.school.backend.domain.gateaway.ActivityStudentParticipantGateway;
import org.school.backend.domain.model.ActivityStudentParticipantModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

import static org.school.backend.adapters.mapper.ActivityStudentParticipantMapper.convertModelsToEntities;
import static org.school.backend.adapters.mapper.ActivityStudentParticipantMapper.toModel;

@Component
public class ActivityStudentParticipantGatewayImpl implements ActivityStudentParticipantGateway {
    private final ActivityStudentParticipantDataSource activityStudentParticipantDataSource;

    public ActivityStudentParticipantGatewayImpl(ActivityStudentParticipantDataSource activityStudentParticipantDataSource){
        this.activityStudentParticipantDataSource = activityStudentParticipantDataSource;
    }

    @Override
    public List<ActivityStudentParticipantModel> getActivityStudent(UUID activityId, int page, int rpp) {
        return toModel(activityStudentParticipantDataSource.getListStudent(activityId,page,rpp));
    }

    @Override
    public void createActivityStudents(List<ActivityStudentParticipantModel> request) {
        activityStudentParticipantDataSource.createActivityStudents(convertModelsToEntities(request));
    }
}
