package org.school.backend.adapters.datasources.impl;

import org.school.backend.adapters.datasources.SubjectDataSource;
import org.school.backend.adapters.datasources.repository.SubjectLogRepository;
import org.school.backend.adapters.dto.SubjectLogs;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SubjectDataSourceImpl implements SubjectDataSource {

    private final SubjectLogRepository subjectLogRepository;

    public SubjectDataSourceImpl(final SubjectLogRepository subjectLogRepository){
        this.subjectLogRepository = subjectLogRepository;
    }

    @Override
    public List<SubjectLogs> findAll(int page, int rpp)
    {
        return this.subjectLogRepository.findAll(page,rpp);
    }

    @Override
    public List<SubjectLogs> getSubject(int page,int rpp,String subject,Boolean isMandatory) {
        return this.subjectLogRepository.getSubject(page, rpp,subject,isMandatory);
    }

    @Override
    public Optional<SubjectLogs> findById(Object id){
        return this.subjectLogRepository.findById(id);
    }

    @Override
    public void deleteById(Object id){
        this.subjectLogRepository.deleteById(id);
    }

    @Override
    public void update(Object id,SubjectLogs record){
        this.subjectLogRepository.update(id,record);
    }

    @Override
    public void create(SubjectLogs record){
        this.subjectLogRepository.create(record);
    }
}
