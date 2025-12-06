package org.school.backend.adapters.datasources.repository;

import jakarta.transaction.Transactional;
import org.school.backend.adapters.schema.jpa.ActivityStudentSummaryJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface JpaActivityStudentParticipantRepository extends JpaRepository<ActivityStudentSummaryJpa, UUID> {
    List<ActivityStudentSummaryJpa> findByActivityId(UUID activityId);
    ActivityStudentSummaryJpa findByActivityIdAndStudentId(UUID activityId, UUID studentId);
}
