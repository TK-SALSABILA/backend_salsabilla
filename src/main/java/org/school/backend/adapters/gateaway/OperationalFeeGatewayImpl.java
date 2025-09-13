package org.school.backend.adapters.gateaway;

import org.school.backend.adapters.datasources.OptFeeDataSource;
import org.school.backend.adapters.mapper.OptFeeMapper;
import org.school.backend.application.usecases.impl.OperationalFeeUseCaseImpl;
import org.school.backend.domain.gateaway.OperationalFeeGateway;
import org.school.backend.domain.model.OperationalFeeModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class OperationalFeeGatewayImpl implements OperationalFeeGateway {

    private final OptFeeDataSource optFeeDataSource;

    public OperationalFeeGatewayImpl(final OptFeeDataSource optFeeDataSource){
        this.optFeeDataSource = optFeeDataSource;
    }
    @Override
    public Optional<List<OperationalFeeModel>> findOperationalfee(int page, int rpp, String studentName, String status, String month, UUID classId) {
        return Optional.of(OptFeeMapper.convertModelsToEntity(optFeeDataSource.findAllFee(page,rpp,studentName,status,month,classId)));
    }

    @Override
    public void createFee(OperationalFeeModel record) {
        optFeeDataSource.create(OptFeeMapper.convertEntityToModel(record));
    }
}
