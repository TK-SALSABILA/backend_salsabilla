package org.school.backend.adapters.datasources.repository;

import jakarta.transaction.Transactional;
import org.school.backend.adapters.schema.jpa.GradeJpa;
import org.school.backend.application.dto.GradeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface JpaGradeRepository extends JpaRepository<GradeJpa, Integer> {
    GradeDto findByStudentId(Integer studentId);
}
