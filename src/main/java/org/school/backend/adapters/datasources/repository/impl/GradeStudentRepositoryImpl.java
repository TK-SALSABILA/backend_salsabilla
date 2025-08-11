package org.school.backend.adapters.datasources.repository.impl;

import org.school.backend.adapters.configuration.ApplicationConfigProperties;
import org.school.backend.adapters.datasources.repository.GradeStudentRepository;
import org.school.backend.adapters.datasources.repository.JpaStudentGradeRepository;
import org.school.backend.domain.model.GradeStudentModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GradeStudentRepositoryImpl implements GradeStudentRepository {
    final ApplicationConfigProperties applicationConfigProperties;
    final JpaStudentGradeRepository jpaStudentGradeRepository;


    public GradeStudentRepositoryImpl(
            ApplicationConfigProperties applicationConfigProperties,
            final JpaStudentGradeRepository jpaStudentGradeRepository
            ){
        this.applicationConfigProperties = applicationConfigProperties;
        this.jpaStudentGradeRepository = jpaStudentGradeRepository;

    }
    @Override
    public GradeStudentModel findStudentByClass(Object classId) {
        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()) {
            case "postgresql" -> {
                List<UUID> studentIds = jpaStudentGradeRepository.findStudentIdsByClassId((UUID) classId);
                return new GradeStudentModel((UUID) classId, studentIds);
            }
            default -> throw new UnsupportedOperationException("Database not supported");
        }
    }
}
