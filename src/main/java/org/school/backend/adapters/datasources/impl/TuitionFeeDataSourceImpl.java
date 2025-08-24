package org.school.backend.adapters.datasources.impl;

import org.school.backend.adapters.datasources.TuitionFeeDataSource;
import org.school.backend.adapters.datasources.repository.TuitionFeeRepository;
import org.school.backend.adapters.dto.TuitionFeeLogReq;
import org.school.backend.adapters.dto.TuitionFeeLogs;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class TuitionFeeDataSourceImpl implements TuitionFeeDataSource {

    final TuitionFeeRepository tuitionFeeRepository;

    public TuitionFeeDataSourceImpl(final TuitionFeeRepository tuitionFeeRepository){
        this.tuitionFeeRepository = tuitionFeeRepository;
    }

    @Override
    public List<TuitionFeeLogs> findTuition(int page, int rpp, String studentName, String status, String month, UUID classId) {
        return  this.tuitionFeeRepository.findTuition(page,rpp,studentName,status,month,classId);
    }

    @Override
    public List<TuitionFeeLogs> findByStudentIdsAndMonthAndStatus(List<UUID> studentIds, String month) {
        return this.tuitionFeeRepository.findByStudentIdsAndMonthAndStatus(studentIds,month);
    }

    @Override
    public void createTuition(TuitionFeeLogReq record) {
        this.tuitionFeeRepository.createTuition(record);
    }

    @Override
    public void saveAllTuition(List<TuitionFeeLogs> record) {
        this.tuitionFeeRepository.saveAllTuition(record);
    }
}
