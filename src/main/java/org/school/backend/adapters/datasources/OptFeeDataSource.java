package org.school.backend.adapters.datasources;

import org.school.backend.adapters.dto.OptFeeLogs;
import org.school.backend.adapters.dto.OptFeeReq;

import java.util.List;
import java.util.UUID;

public interface OptFeeDataSource {
    List<OptFeeLogs> findAllFee(int page, int rpp, String studentName, String status, String month, UUID classId);
    void create(OptFeeReq record);
}
