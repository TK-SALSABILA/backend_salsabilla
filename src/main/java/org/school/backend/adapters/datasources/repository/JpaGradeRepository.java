package org.school.backend.adapters.datasources.repository;

import jakarta.transaction.Transactional;
import org.school.backend.adapters.schema.jpa.GradeJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@Transactional
public interface JpaGradeRepository extends JpaRepository<GradeJpa, UUID> {
}
