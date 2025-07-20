package org.school.backend.adapters.datasources.impl;

import org.school.backend.adapters.datasources.ParentDataSource;
import org.school.backend.adapters.datasources.repository.ParentRepository;
import org.school.backend.adapters.dto.ParentLogs;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ParentDataSourceImpl implements ParentDataSource {

    private final ParentRepository parentRepository;

    public ParentDataSourceImpl(ParentRepository parentRepository){
        this.parentRepository = parentRepository;
    }
    @Override
    public Optional<ParentLogs> findByStudentId(Object id){
        return parentRepository.findByStudentId(id);
    }
}
