package org.school.backend.adapters.gateaway;

import org.school.backend.adapters.datasources.ActivityStudentParticipantDataSource;
import org.school.backend.domain.gateaway.ActivityStudentParticipantGateway;
import org.school.backend.domain.model.ActivityStudentParticipantModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.school.backend.adapters.mapper.ActivityStudentParticipantMapper.*;

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
    public Optional<ActivityStudentParticipantModel> getStudentById(UUID activityId, UUID studentId) {
        return Optional.of(convertEntityToModel(activityStudentParticipantDataSource.getStudentById(activityId,studentId)));
    }

    @Override
    public void createActivityStudents(List<ActivityStudentParticipantModel> request) {
        activityStudentParticipantDataSource.createActivityStudents(convertModelsToEntities(request));
    }

    @Override
    public void updateActivityStudents(UUID activityId, UUID studentId, int amount) {
        activityStudentParticipantDataSource.updateActivityStudents(activityId,studentId,amount);
    }
}
