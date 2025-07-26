package org.school.backend.adapters.datasources.impl;

import org.school.backend.adapters.datasources.repository.SavingLogsRepository;
import org.school.backend.adapters.datasources.SavingDataSource;
import org.school.backend.adapters.dto.SavingLogReq;
import org.school.backend.adapters.dto.SavingLogs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SavingDataSourceImpl implements SavingDataSource {
    final SavingLogsRepository savingLogsRepository;

    public SavingDataSourceImpl(final SavingLogsRepository savingLogsRepository){
        this.savingLogsRepository = savingLogsRepository;
    }
    @Override
    public List<SavingLogs> findAll(int page, int rpp) {
        return savingLogsRepository.findAll(page,rpp);
    }

    @Override
    public void create(SavingLogReq record) {
        savingLogsRepository.create(record);
    }
}
