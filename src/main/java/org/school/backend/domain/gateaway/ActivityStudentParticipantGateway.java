package org.school.backend.domain.gateaway;

import org.school.backend.domain.model.ActivityStudentParticipantModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActivityStudentParticipantGateway {
    List<ActivityStudentParticipantModel> getActivityStudent(UUID activityId, int page, int rpp);
    Optional<ActivityStudentParticipantModel> getStudentById(UUID activityId,UUID studentId);
    void createActivityStudents(List<ActivityStudentParticipantModel> request);
    void updateActivityStudents(UUID activityId, UUID studentId, int amount);
}
