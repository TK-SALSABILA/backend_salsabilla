package org.school.backend.adapters.datasources.repository;

import jakarta.transaction.Transactional;
import org.school.backend.adapters.schema.jpa.SavingLogJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
@Transactional
public interface JpaSavingLogsRepository extends JpaRepository<SavingLogJpa, UUID> {

    @Query("""
    SELECT SUM(s.amount)
    FROM SavingLogJpa s
    WHERE s.studentId = :studentId
    """)
    Integer sumAmountByStudentId(UUID studentId);
}
