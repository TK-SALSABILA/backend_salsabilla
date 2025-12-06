package org.school.backend.adapters.datasources.impl;

import org.school.backend.adapters.datasources.ActivityStudentParticipantDataSource;
import org.school.backend.adapters.datasources.repository.ActivityStudentParticipantRepository;
import org.school.backend.adapters.dto.ActivityClassParticipant;
import org.school.backend.adapters.dto.ActivityStudentParticipant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ActivityStudentParticipantDataSourceImpl implements ActivityStudentParticipantDataSource {
    private ActivityStudentParticipantRepository activityStudentParticipantRepository;

    public ActivityStudentParticipantDataSourceImpl(final ActivityStudentParticipantRepository activityStudentParticipantRepository){
        this.activityStudentParticipantRepository = activityStudentParticipantRepository;
    }


    @Override
    public List<ActivityStudentParticipant> getListStudent(UUID activityId, int page, int rpp) {
        return activityStudentParticipantRepository.getListStudent(activityId,page, rpp);
    }

    @Override
    public ActivityStudentParticipant getStudentById(UUID activityId, UUID studentId) {
        return activityStudentParticipantRepository.getStudentById(activityId,studentId);
    }

    @Override
    public void createActivityStudents(List<ActivityStudentParticipant> request) {
        activityStudentParticipantRepository.createActivityStudents(request);
    }

    @Override
    public void updateActivityStudents(UUID activityId, UUID studentId, int amount) {
        activityStudentParticipantRepository.updateActivityStudents(activityId,studentId,amount);
    }
}
