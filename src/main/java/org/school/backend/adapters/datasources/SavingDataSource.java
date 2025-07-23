package org.school.backend.adapters.datasources;

import org.school.backend.adapters.dto.SavingLogReq;
import org.school.backend.adapters.dto.SavingLogs;

import java.util.List;

public interface SavingDataSource {
    List<SavingLogs> findAll(int page, int rpp);
    void create(SavingLogReq record);
}
