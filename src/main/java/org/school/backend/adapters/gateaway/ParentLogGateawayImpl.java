package org.school.backend.adapters.gateaway;

import org.school.backend.adapters.datasources.ParentDataSource;
import org.school.backend.adapters.dto.ParentLogs;
import org.school.backend.domain.gateaway.ParentLogGateaway;
import org.school.backend.domain.model.ParentModel;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.school.backend.adapters.mapper.ParentLogsMapper.convertEntityToModel;
import static org.school.backend.adapters.mapper.ParentLogsMapper.convertModelToEntity;

@Component
public class ParentLogGateawayImpl implements ParentLogGateaway {
    private final ParentDataSource parentDataSource;

    public ParentLogGateawayImpl(final ParentDataSource parentDataSource){
        this.parentDataSource = parentDataSource;
    }
    @Override
    public Optional<ParentModel> findByStudentId(Object id){
        return convertModelToEntity(parentDataSource.findByStudentId(id));
    }

    @Override
    public void update(Object id, ParentModel record) {
        ParentLogs data = convertEntityToModel(record);
        parentDataSource.updateByStudentId(id, data);
    }
}
