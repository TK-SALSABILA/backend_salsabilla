package org.school.backend.adapters.gateaway;

import org.school.backend.adapters.datasources.SubjectDataSource;
import org.school.backend.adapters.dto.SubjectLogs;
import org.school.backend.adapters.mapper.SubjectLogMapper;
import org.school.backend.domain.gateaway.SubjectLogGateaway;
import org.school.backend.domain.model.SubjectModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.school.backend.adapters.mapper.SubjectLogMapper.convertEntityToModel;

@Component
public class SubjectLogGateawayImpl implements SubjectLogGateaway {
    private final SubjectDataSource subjectDataSource;

    public SubjectLogGateawayImpl(final SubjectDataSource subjectDataSource){
        this.subjectDataSource = subjectDataSource;
    }

    @Override
    public Optional<List<SubjectModel>> findAll (int page, int rpp){
        return Optional.of(SubjectLogMapper.convertModelsToEntity(subjectDataSource.findAll(page, rpp)));
    }

    @Override
    public Optional<SubjectModel> findById(Object id){
        return SubjectLogMapper.convertModelToEntity(subjectDataSource.findById(id));
    }

    @Override
    public void update(Object id,SubjectModel record){
       var data = SubjectLogMapper.convertEntityToModel(record);
        subjectDataSource.update(id,data);
    }

    @Override
    public void create(SubjectModel record){
        subjectDataSource.create(convertEntityToModel(record));
    }


}
