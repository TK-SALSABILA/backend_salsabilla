package org.school.backend.adapters.datasources.repository;

import org.school.backend.adapters.configuration.ApplicationConfigProperties;
import org.school.backend.adapters.dto.GradeLogs;
import org.school.backend.adapters.schema.jpa.GradeJpa;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class GradeRepositoryImpl implements GradeRepository{
    final ApplicationConfigProperties applicationConfigProperties;
    final JpaGradeRepository jpaGradeRepository;

    public GradeRepositoryImpl(
            ApplicationConfigProperties applicationConfigProperties,
            final JpaGradeRepository jpaGradeRepository
    ){
        this.applicationConfigProperties =applicationConfigProperties;
        this.jpaGradeRepository = jpaGradeRepository;
    }

    @Override
    public List<GradeLogs> findAll(int page, int rpp)
    {
        List<GradeLogs> results = new ArrayList<>();
        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()){
            case "postgresql" -> jpaGradeRepository.findAll()
                    .forEach(entity-> results.add(new GradeLogs(
                            entity.getId(),
                            entity.getGradeLevel()

                    )));
//            case "elasticsearch" -> elasticSearchRepository.findAll()
//                    .forEach(entity-> results.add(new GradeLogs(
//                    entity.getGradeLevel()
//
//            )));
            default -> throw new IllegalArgumentException(applicationConfigProperties.getDatabaseDefault().toLowerCase());
        }
        return  results;

    }

    @Override
    public void create(GradeLogs record){
        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()) {
            case "postgresql" -> {
               GradeJpa subjectLogs = new GradeJpa(

                        record.getGradeLevel()

                );
                jpaGradeRepository.save(subjectLogs);
            }
//            case "elasticsearch" -> {
//                FlightLogsEs dataEs = new FlightLogsEs(UUID.randomUUID().toString(), record.getFlyTo(), record.getCurrency(), record.getDateTo(), record.getDateFrom(), record.getRecordDateTime());
//                elasticSearchRepository.save(dataEs);
//            }
            default ->
                    throw new IllegalArgumentException(applicationConfigProperties.getDatabaseDefault().toLowerCase());
        }
    }
}
