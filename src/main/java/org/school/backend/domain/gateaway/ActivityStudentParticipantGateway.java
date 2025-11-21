package org.school.backend.domain.gateaway;

import org.school.backend.domain.model.ActivityStudentParticipantModel;

import java.util.List;
import java.util.UUID;

public interface ActivityStudentParticipantGateway {
    List<ActivityStudentParticipantModel> getActivityStudent(UUID activityId, int page, int rpp);
    void createActivityStudents(List<ActivityStudentParticipantModel> request);
}
