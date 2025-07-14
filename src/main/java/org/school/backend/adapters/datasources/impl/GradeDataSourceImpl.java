package org.school.backend.adapters.datasources.impl;

import org.school.backend.adapters.datasources.GradeDataSource;
import org.school.backend.adapters.datasources.repository.GradeRepository;
import org.school.backend.adapters.dto.GradeLogs;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GradeDataSourceImpl implements GradeDataSource {
    final GradeRepository gradeRepository;

    public GradeDataSourceImpl(    final GradeRepository gradeRepository){
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<GradeLogs> findAll(int page, int rpp){
        return this.gradeRepository.findAll(page,rpp);
    }
    @Override
    public void create(GradeLogs record){
      this.gradeRepository.create(record);
    }
}
