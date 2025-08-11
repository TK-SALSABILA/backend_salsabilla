package org.school.backend.application.usecases;

import org.school.backend.application.dto.request.SavingRequestDto;
import org.school.backend.application.dto.response.SavingLogOutputDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SavingLogsUseCase {
    Optional<List<SavingLogOutputDto>> findAll(int page, int rpp);
    void create(SavingRequestDto record);
    Integer checkBalance(UUID studentId);
}
