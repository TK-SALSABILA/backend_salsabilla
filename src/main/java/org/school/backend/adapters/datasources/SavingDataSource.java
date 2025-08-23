package org.school.backend.adapters.datasources;

import org.school.backend.adapters.dto.SavingLogReq;
import org.school.backend.adapters.dto.SavingLogs;

import java.util.List;
import java.util.UUID;

public interface SavingDataSource {
    List<SavingLogs> findAll(int page, int rpp);
    List<SavingLogs> findSaving(int page, int rpp, String studentName, String status, String month, UUID classId);
    void create(SavingLogReq record);
    Integer getBalance(UUID studentId);
    void withDrawSaving(UUID studentId, Integer amount, String description);
}
