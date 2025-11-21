package org.school.backend.adapters.datasources.impl;

import org.school.backend.adapters.datasources.ActivityClassParticipantDataSource;
import org.school.backend.adapters.datasources.repository.ActivityClassParticipantRepository;
import org.school.backend.adapters.dto.ActivityClassParticipant;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActivityClassParticipantDataSourceImpl implements ActivityClassParticipantDataSource {
    private ActivityClassParticipantRepository activityClassParticipantRepository;

    public ActivityClassParticipantDataSourceImpl(final ActivityClassParticipantRepository activityClassParticipantRepository){
        this.activityClassParticipantRepository = activityClassParticipantRepository;
    }
    @Override
    public void createClassParticipant(List<ActivityClassParticipant> request) {
        activityClassParticipantRepository.create(request);
    }
}
