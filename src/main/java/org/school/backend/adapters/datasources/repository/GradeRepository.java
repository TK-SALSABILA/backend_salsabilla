package org.school.backend.adapters.datasources.repository;

import org.school.backend.application.dto.GradeDto;

import java.util.Optional;

public interface GradeRepository {
    Optional<GradeDto> findByStudentId(Integer studentId);
}
