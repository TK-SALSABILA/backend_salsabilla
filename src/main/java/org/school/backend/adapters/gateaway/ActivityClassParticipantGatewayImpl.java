package org.school.backend.adapters.gateaway;

import org.school.backend.adapters.datasources.ActivityClassParticipantDataSource;
import org.school.backend.adapters.mapper.ActivityClassParticipantMapper;
import org.school.backend.domain.gateaway.ActivityClassParticipantGateway;
import org.school.backend.domain.model.ActivityClassParticipantModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActivityClassParticipantGatewayImpl implements ActivityClassParticipantGateway {
    private ActivityClassParticipantDataSource activityClassParticipantDataSource;

    public ActivityClassParticipantGatewayImpl(ActivityClassParticipantDataSource activityClassParticipantDataSource){
        this.activityClassParticipantDataSource = activityClassParticipantDataSource;
    }

    @Override
    public void createClassParticipant(List<ActivityClassParticipantModel> request) {
        activityClassParticipantDataSource.createClassParticipant(ActivityClassParticipantMapper.toDto(request));
    }
}
