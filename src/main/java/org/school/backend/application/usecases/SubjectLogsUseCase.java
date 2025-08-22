package org.school.backend.application.usecases;

import org.school.backend.application.dto.request.SubjectParamDto;
import org.school.backend.application.dto.response.SubjectLogsDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SubjectLogsUseCase {
    Optional<List<SubjectLogsDto>> findAll(SubjectParamDto params);
    Optional<SubjectLogsDto> findById(UUID id);
    boolean deleteById(UUID id);
    void update(UUID id, SubjectLogsDto record);
    void create(SubjectLogsDto record);
}
