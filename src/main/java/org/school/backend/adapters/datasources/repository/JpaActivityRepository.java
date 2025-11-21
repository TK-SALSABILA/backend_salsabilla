package org.school.backend.adapters.datasources.repository;

import jakarta.transaction.Transactional;
import org.school.backend.adapters.schema.jpa.ActivityJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
@Transactional
public interface JpaActivityRepository extends JpaRepository<ActivityJpa, UUID> {
    @Query(value = """
        SELECT 
            a.id,
            a.activity_name,
            a.activity_date,
            a.description,
            a.total_fund_required,
            a.total_fund_raised,
            a.is_active,
            acp.grade_id,
            acp.grade_name
        FROM activity a
        LEFT JOIN activity_class_participant acp ON a.id = acp.activity_id
        ORDER BY a.activity_date DESC
        """, nativeQuery = true)
    List<Map<String, Object>> findAllWithClassParticipantsNative();
}
