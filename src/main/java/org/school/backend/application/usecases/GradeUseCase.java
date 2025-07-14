package org.school.backend.application.usecases;

import org.school.backend.application.dto.GradeDto;

import java.util.List;
import java.util.Optional;

public interface GradeUseCase {
    Optional<List<GradeDto>> findAll(int page, int rpp);
    void create(GradeDto record);
}
