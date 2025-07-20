package org.school.backend.application.usecases;

import org.school.backend.application.dto.response.SubjectLogsDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubjectLogsUseCase {
    Optional<List<SubjectLogsDto>> findAll(int page, int rpp);
    Optional<SubjectLogsDto> findById(UUID id);
    void update(UUID id, SubjectLogsDto record);
    void create(SubjectLogsDto record);
}
