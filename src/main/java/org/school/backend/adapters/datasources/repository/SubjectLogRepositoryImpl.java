package org.school.backend.adapters.datasources.repository;

import org.school.backend.adapters.configuration.ApplicationConfigProperties;
import org.school.backend.adapters.dto.StudentLogs;
import org.school.backend.adapters.dto.SubjectLogs;
import org.school.backend.adapters.schema.jpa.SubjectLogJpa;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class SubjectLogRepositoryImpl implements SubjectLogRepository{

    final ApplicationConfigProperties applicationConfigProperties;
    final JpaSubjectRepository jpaSubjectRepository;

    public SubjectLogRepositoryImpl(
            ApplicationConfigProperties applicationConfigProperties,
            final JpaSubjectRepository jpaSubjectRepository
    ){
        this.applicationConfigProperties =applicationConfigProperties;
        this.jpaSubjectRepository = jpaSubjectRepository;
    }

    @Override
    public List<SubjectLogs> findAll(int rpp, int page){
        List<SubjectLogs> results = new ArrayList<>();

        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()){
            case "postgresql" -> jpaSubjectRepository.findAll()
                    .forEach(entity-> results.add(new SubjectLogs(
//                            entity.getId(),
                            entity.getSubjectName(),
                            entity.getSubjectCode(),
                            entity.getGradeLevel(),
                            entity.getIsMandatory(),
                            entity.getDescription()
                    )));
//            case "elasticsearch" -> elasticSearchRepository.findAll()
//                    .forEach(entity-> results.add(new StudentLogs(
//                            entity.getId(),
//                            entity.getFullName(),
//                            entity.getNickName(),
//                            entity.getNik(),
//                            entity.getGender(),
//                            entity.getDateBirth(),
//                            entity.getBirthOrder()
//                    )));
            default -> throw new IllegalArgumentException(applicationConfigProperties.getDatabaseDefault().toLowerCase());
        }
        return  results;
    }

    @Override
    public  Optional<SubjectLogs> findById(Object id){
        SubjectLogs result;

        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()) {
            case "postgresql" -> {
                Optional<SubjectLogJpa> resultsJpa = jpaSubjectRepository.findById((UUID) id);
                result = new SubjectLogs(
                        resultsJpa.get().getSubjectName(),
                        resultsJpa.get().getSubjectCode(),
                        resultsJpa.get().getGradeLevel(),
                        resultsJpa.get().getIsMandatory(),
                        resultsJpa.get().getDescription()
                );
            }
//            case "elasticsearch" -> {
//                Optional<FlightLogsEs> resultEs = elasticSearchRepository.findById(String.valueOf(id));
//                result = new FlightLogs(resultEs.get().getFlyTo(), resultEs.get().getCurrency(), resultEs.get().getDateTo(), resultEs.get().getDateFrom(), resultEs.get().getRecordDateTime());
//            }
            default ->
                    throw new IllegalArgumentException(applicationConfigProperties.getDatabaseDefault().toLowerCase());
        }

        return Optional.of(result);
    }

    @Override
    public void update(Object id, SubjectLogs record) {
        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()) {
            case "postgresql" -> {
                Optional<SubjectLogJpa> optionalEntity = jpaSubjectRepository.findById((UUID) id);

                if (optionalEntity.isPresent()) {
                    SubjectLogJpa entity = optionalEntity.get();

                    entity.setSubjectName(record.getSubjectName());
                    entity.setSubjectCode(record.getSubjectCode());
                    entity.setGradeLevel(record.getGradeLevel());
                    entity.setIsMandatory(record.getIsMandatory());
                    entity.setDescription(record.getDescription());

                    jpaSubjectRepository.save(entity);
                } else {
                    throw new RuntimeException("Subject not found with id: " + id);
                }
            }
            // case "elasticsearch" -> { ... }
            default -> throw new IllegalArgumentException(applicationConfigProperties.getDatabaseDefault().toLowerCase());
        }
    }


    @Override
    public void create(SubjectLogs record){

        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()) {
            case "postgresql" -> {
                SubjectLogJpa subjectLogs = new SubjectLogJpa(
                        record.getSubjectName(),
                        record.getSubjectCode(),
                        record.getGradeLevel(),
                        record.getIsMandatory(),
                        record.getDescription()
                );
                jpaSubjectRepository.save(subjectLogs);
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
