package org.school.backend.adapters.gateaway;

import org.school.backend.adapters.datasources.SavingDataSource;
import org.school.backend.adapters.mapper.SavingLogsMapper;
import org.school.backend.domain.gateaway.SavingLogGateaway;
import org.school.backend.domain.model.SavingModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.school.backend.adapters.mapper.SavingLogsMapper.convertModelsToEntity;

@Component
public class SavingLogGateawayImpl implements SavingLogGateaway {
    final SavingDataSource savingDataSource;

    public SavingLogGateawayImpl(final SavingDataSource savingDataSource){
        this.savingDataSource = savingDataSource;
    }

    @Override
    public Optional<List<SavingModel>> findAll(int page, int rpp) {
        return Optional.of(convertModelsToEntity(savingDataSource.findAll(page,rpp)));
    }

    @Override
    public Optional<List<SavingModel>> findSavings(int page, int rpp,String studentName, String status, String month, UUID classId) {
        return Optional.of(convertModelsToEntity(savingDataSource.findSaving(page, rpp, studentName,status,month,classId)));
    }

    @Override
    public void create(SavingModel record) {
        savingDataSource.create(SavingLogsMapper.convertEntityToModel(record));
    }

    @Override
    public Integer reduction(UUID studentId) {
      return savingDataSource.getBalance(studentId);
    }

    @Override
    public void withDrawSaving(UUID studentId, Integer amount, String description) {
         savingDataSource.withDrawSaving(studentId,amount,description);
    }
}
