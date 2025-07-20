package org.school.backend.adapters.datasources.repository;

import org.school.backend.application.dto.StudentGradeDto;

import java.util.Optional;

public interface StudentGradeRepository {
    Optional<StudentGradeDto> findByStudentId(Integer studentId);
}
