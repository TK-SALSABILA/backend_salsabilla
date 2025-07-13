package org.school.backend.application.usecases;

import org.school.backend.application.dto.StudentGradeDto;

import java.util.Optional;

public interface GradeUseCase {

    Optional<StudentGradeDto> findByStudentId(Integer id);
}
