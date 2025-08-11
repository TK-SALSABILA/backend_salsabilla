package org.school.backend.adapters.datasources.impl;

import org.school.backend.adapters.datasources.GradeDataSource;
import org.school.backend.adapters.datasources.GradeStudentDataSource;
import org.school.backend.adapters.datasources.repository.GradeStudentRepository;
import org.school.backend.domain.model.GradeStudentModel;
import org.springframework.stereotype.Component;

@Component
public class GradeStudentDataSourceImpl implements GradeStudentDataSource {

    final GradeStudentRepository gradeStudentRepository;

    public GradeStudentDataSourceImpl(final GradeStudentRepository gradeStudentRepository){
        this.gradeStudentRepository = gradeStudentRepository;
    }

    @Override
    public GradeStudentModel findStudentByClass(Object classId) {
        return gradeStudentRepository.findStudentByClass(classId);
    }
}
