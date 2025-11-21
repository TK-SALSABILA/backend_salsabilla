package org.school.backend.adapters.datasources.repository;

import org.school.backend.adapters.dto.ActivityStudentParticipant;

import java.util.List;
import java.util.UUID;

public interface ActivityStudentParticipantRepository {
    List<ActivityStudentParticipant> getListStudent(UUID activityId, int page, int rpp);
    void createActivityStudents(List<ActivityStudentParticipant> request);
}
