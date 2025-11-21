package org.school.backend.adapters.datasources.repository;

import org.school.backend.adapters.dto.ActivityClassParticipant;

import java.util.List;

public interface ActivityClassParticipantRepository {
    void create(List<ActivityClassParticipant> request);
}
