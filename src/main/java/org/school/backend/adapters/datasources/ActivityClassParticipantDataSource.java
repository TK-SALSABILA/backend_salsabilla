package org.school.backend.adapters.datasources;

import org.school.backend.adapters.dto.ActivityClassParticipant;

import java.util.List;

public interface ActivityClassParticipantDataSource {
    void createClassParticipant(List<ActivityClassParticipant> request);
}
