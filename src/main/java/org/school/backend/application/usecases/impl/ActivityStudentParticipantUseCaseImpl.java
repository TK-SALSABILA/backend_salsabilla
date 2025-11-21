package org.school.backend.application.usecases.impl;

import org.school.backend.application.dto.request.ActivityStudentParticipantRequestDto;
import org.school.backend.application.dto.response.ActivityStudentParticipantResponseDto;
import org.school.backend.application.mappers.ActivityStudentParticipantMapper;
import org.school.backend.application.usecases.ActivityStudentParticipantUseCase;
import org.school.backend.domain.gateaway.ActivityStudentParticipantGateway;

import java.util.List;
import java.util.UUID;

public class ActivityStudentParticipantUseCaseImpl implements ActivityStudentParticipantUseCase {

    private ActivityStudentParticipantGateway activityStudentParticipantGateway;

    public ActivityStudentParticipantUseCaseImpl(ActivityStudentParticipantGateway activityStudentParticipantGateway){
        this.activityStudentParticipantGateway = activityStudentParticipantGateway;
    }

    @Override
    public List<ActivityStudentParticipantResponseDto> getListStudent(UUID activityId, int page, int rpp) {
        return ActivityStudentParticipantMapper.toDto(activityStudentParticipantGateway.getActivityStudent(activityId,page,rpp));
    }

    @Override
    public void createActivityStudent(List<ActivityStudentParticipantRequestDto> record) {
        activityStudentParticipantGateway.createActivityStudents(ActivityStudentParticipantMapper.convertModelsToEntities(record));
    }
}
