package org.school.backend.adapters.datasources.impl;

import org.school.backend.adapters.datasources.StudentDataSource;
import org.school.backend.adapters.datasources.repository.StudentLogsRepository;
import org.school.backend.adapters.dto.StudentDetails;
import org.school.backend.adapters.dto.StudentLogs;
import org.school.backend.adapters.dto.StudentRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class StudentDataSourceImpl implements StudentDataSource {
    final StudentLogsRepository studentLogsRepository;

    public StudentDataSourceImpl(StudentLogsRepository studentLogsRepository) {
        this.studentLogsRepository = studentLogsRepository;
    }

    @Override
    public List<StudentLogs> findAll(int rpp,int page){
        return studentLogsRepository.findAll(rpp,page);
    }

    @Override
    public List<StudentLogs> findByFilter(String studentName, UUID classId){
        return studentLogsRepository.findByFilter(studentName,classId);
    }

    @Override
    public Optional<StudentDetails> findById(Object id){
        return studentLogsRepository.findById(id);
    }

    @Override
    public void create(StudentRequest record){
         studentLogsRepository.create(record);
    }

    @Override
    public void update(Object id, StudentDetails record) {
        studentLogsRepository.update(id, record);
    }
}
