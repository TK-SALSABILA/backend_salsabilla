package org.school.backend.adapters.datasources.repository;

import jakarta.transaction.Transactional;
import org.school.backend.adapters.schema.jpa.StudentGradeJpa;
import org.school.backend.application.dto.StudentGradeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface JpaStudentGradeRepository extends JpaRepository<StudentGradeJpa, UUID> {
    Optional<StudentGradeJpa> findByStudentId(UUID studentId);
}
