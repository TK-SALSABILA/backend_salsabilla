package org.school.backend.adapters.gateaway;

import org.school.backend.adapters.datasources.GradeDataSource;
import org.school.backend.adapters.mapper.GradeMapper;
import org.school.backend.domain.gateaway.GradeLogGateaway;
import org.school.backend.domain.model.GradeModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static org.school.backend.adapters.mapper.GradeMapper.convertEntityToModel;

@Component
public class GradeLogGateawayImpl implements GradeLogGateaway {

    final GradeDataSource gradeDataSource;

    public GradeLogGateawayImpl(final GradeDataSource gradeDataSource){
        this.gradeDataSource = gradeDataSource;
    }

    @Override
    public Optional<List<GradeModel>> findAll(int page, int rpp){
        return Optional.of(GradeMapper.convertModelsToEntity(gradeDataSource.findAll(page,rpp)));
    }
    @Override
    public void create(GradeModel record){
        gradeDataSource.create(convertEntityToModel(record));
    }
}
