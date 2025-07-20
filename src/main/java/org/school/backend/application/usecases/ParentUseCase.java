package org.school.backend.application.usecases;

import org.school.backend.application.dto.ParentDto;

import java.util.Optional;
import java.util.UUID;

public interface ParentUseCase {
    Optional<ParentDto> findByStudentId(UUID id);
}
