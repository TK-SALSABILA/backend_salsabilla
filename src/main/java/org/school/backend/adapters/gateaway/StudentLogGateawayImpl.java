package org.school.backend.adapters.gateaway;

import org.school.backend.adapters.datasources.StudentDataSource;
import org.school.backend.domain.gateaway.StudentLogGateaway;
import org.school.backend.domain.model.StudentModel;
import org.springframework.stereotype.Component;

import static org.school.backend.adapters.mapper.StudentRecordEntityMapper.convertModelToEntity;
import static org.school.backend.adapters.mapper.StudentRecordEntityMapper.convertModelsToEntity;

import java.util.List;
import java.util.Optional;

@Component
public class StudentLogGateawayImpl implements StudentLogGateaway {

    private final StudentDataSource studentRecordDataSource;

    public StudentLogGateawayImpl(final StudentDataSource studentRecordDataSource){
        this.studentRecordDataSource = studentRecordDataSource;
    }

    @Override
    public Optional<List<StudentModel>> findAll(int page, int rpp){
        return Optional.of(convertModelsToEntity(studentRecordDataSource.findAll(page,rpp)));
    }

    @Override
    public Optional<StudentModel> findById(Object id){
        return convertModelToEntity(studentRecordDataSource.findById(id));
    }
}
