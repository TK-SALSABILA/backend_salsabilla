package org.school.backend.adapters.datasources.impl;

import org.school.backend.adapters.datasources.OptFeeDataSource;
import org.school.backend.adapters.datasources.repository.OptFeeLogRepository;
import org.school.backend.adapters.dto.OptFeeLogs;
import org.school.backend.adapters.dto.OptFeeReq;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class OptFeeDataSourceImpl implements OptFeeDataSource {

    private OptFeeLogRepository optFeeLogRepository;

    public OptFeeDataSourceImpl(OptFeeLogRepository optFeeLogRepository){
        this.optFeeLogRepository = optFeeLogRepository;
    }

    @Override
    public List<OptFeeLogs> findAllFee(int page, int rpp, String studentName, String status, String month, UUID classId) {
        return optFeeLogRepository.findFee(page, rpp, studentName, status, month, classId);
    }

    @Override
    public void create(OptFeeReq record) {
        optFeeLogRepository.create(record);
    }
}
