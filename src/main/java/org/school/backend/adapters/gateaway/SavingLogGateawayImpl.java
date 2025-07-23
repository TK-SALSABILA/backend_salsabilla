package org.school.backend.adapters.gateaway;

import org.school.backend.adapters.datasources.SavingDataSource;
import org.school.backend.adapters.mapper.SavingLogsMapper;
import org.school.backend.domain.gateaway.SavingLogGateaway;
import org.school.backend.domain.model.SavingModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.school.backend.adapters.mapper.SavingLogsMapper.convertModelsToEntity;

@Component
public class SavingLogGateawayImpl implements SavingLogGateaway {
    final SavingDataSource savingDataSource;

    public SavingLogGateawayImpl(final SavingDataSource savingDataSource){
        this.savingDataSource = savingDataSource;
    }
    @Override
    public Optional<List<SavingModel>> findAll(int page, int rpp) {
        System.out.println(savingDataSource.findAll(page,rpp) + "ini savbing log gateawayimpl");
        return Optional.of(convertModelsToEntity(savingDataSource.findAll(page,rpp)));
    }

    @Override
    public void create(SavingModel record) {
        savingDataSource.create(SavingLogsMapper.convertEntityToModel(record));
    }
}
