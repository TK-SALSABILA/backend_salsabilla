package org.school.backend.adapters.gateaway;

import org.school.backend.adapters.datasources.StudentDataSource;
import org.school.backend.adapters.dto.StudentDetails;
import org.school.backend.domain.gateaway.StudentLogGateaway;
import org.school.backend.domain.model.StudentModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.school.backend.adapters.mapper.StudentRecordEntityMapper.*;

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
    public Optional<List<StudentModel>> getStudent(int page, int rpp, String student,  UUID classId) {
        return Optional.of(convertModelsToEntity(studentRecordDataSource.getStudent(page, rpp, student, classId)));
    }

    @Override
    public Optional<StudentModel> findById(Object id){
        return convertModelToEntity(studentRecordDataSource.findById(id));
    }

    @Override
    public void create(StudentModel record){
        studentRecordDataSource.create(convertEntityToModel(record));
    }

    @Override
    public void update(Object id, StudentModel record) {
        StudentDetails data = convertEntityToModelsDetails(record);
        studentRecordDataSource.update(id,data);
    }
}
