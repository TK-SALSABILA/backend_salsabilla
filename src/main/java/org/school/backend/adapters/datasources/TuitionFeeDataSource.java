package org.school.backend.adapters.datasources;

import org.school.backend.adapters.dto.TuitionFeeLogReq;
import org.school.backend.adapters.dto.TuitionFeeLogs;


import java.util.List;
import java.util.UUID;

public interface TuitionFeeDataSource {
    List<TuitionFeeLogs> findByStudentIdsAndMonthAndStatus(List<UUID> studentIds, String month);
    void createTuition(TuitionFeeLogReq record);
    void saveAllTuition(List<TuitionFeeLogs> fee);
}
