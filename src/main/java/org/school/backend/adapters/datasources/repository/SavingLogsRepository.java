package org.school.backend.adapters.datasources.repository;

import org.school.backend.adapters.dto.SavingLogReq;
import org.school.backend.adapters.dto.SavingLogs;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SavingLogsRepository {
    List<SavingLogs> findAll(int page,int rpp);
    void create(SavingLogReq record);
    Integer getBalance(UUID studentId);
    void withDrawSaving(UUID studentId, Integer amount, String description);
}
