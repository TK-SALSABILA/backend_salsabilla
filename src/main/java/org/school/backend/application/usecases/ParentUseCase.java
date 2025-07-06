package org.school.backend.application.usecases;

import org.school.backend.application.dto.ParentDto;

import java.util.Optional;

public interface ParentUseCase {
    Optional<ParentDto> findByStudentId(Integer id);
}
