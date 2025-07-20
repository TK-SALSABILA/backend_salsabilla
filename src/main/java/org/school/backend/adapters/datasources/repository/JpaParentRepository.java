package org.school.backend.adapters.datasources.repository;

import jakarta.transaction.Transactional;
import org.school.backend.adapters.dto.ParentLogs;
import org.school.backend.adapters.schema.jpa.ParentJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface JpaParentRepository extends JpaRepository<ParentJpa, Integer> {
    Optional<ParentJpa> findByStudentId(UUID studentId);
}
