package org.school.backend.application.usecases;

import org.school.backend.application.dto.GradeDto;

import java.util.Optional;

public interface GradeUseCase {

    Optional<GradeDto> findByStudentId(Integer id);
}
