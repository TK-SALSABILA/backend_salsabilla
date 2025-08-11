package org.school.backend.adapters.gateaway;

import org.school.backend.adapters.datasources.TuitionFeeDataSource;
import org.school.backend.adapters.mapper.TuitionFeeMapper;
import org.school.backend.domain.gateaway.TuitionFeeLogGateaway;
import org.school.backend.domain.model.TuitionFeeModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.school.backend.adapters.mapper.TuitionFeeMapper.convertModelsToEntities;
import static org.school.backend.adapters.mapper.TuitionFeeMapper.convertModelsToEntity;

@Component
public class TuitionFeeLogGateawayImpl implements TuitionFeeLogGateaway {
    final TuitionFeeDataSource tuitionFeeDataSource;

    public TuitionFeeLogGateawayImpl(final TuitionFeeDataSource tuitionFeeDataSource){
        this.tuitionFeeDataSource = tuitionFeeDataSource;
    }

    @Override
    public Optional<List<TuitionFeeModel>> findByStudentIdsAndMonthAndStatus(List<UUID> studentIds, String month) {
        return Optional.of(convertModelsToEntity(tuitionFeeDataSource.findByStudentIdsAndMonthAndStatus(studentIds,month)));
    }

    @Override
    public void createTuition(TuitionFeeModel record) {
        tuitionFeeDataSource.createTuition(TuitionFeeMapper.convertEntityToModel(record));
    }

    @Override
    public void saveAll(List<TuitionFeeModel> fee) {
        tuitionFeeDataSource.saveAllTuition(convertModelsToEntities(fee));
    }
}
