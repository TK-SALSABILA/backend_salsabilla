package org.school.backend.adapters.datasources.repository.impl;

import org.school.backend.adapters.configuration.ApplicationConfigProperties;
import org.school.backend.adapters.datasources.repository.JpaParentRepository;
import org.school.backend.adapters.datasources.repository.ParentRepository;
import org.school.backend.adapters.dto.ParentLogs;
import org.school.backend.adapters.schema.jpa.ParentJpa;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class ParentRepositoryImpl implements ParentRepository {

    private final ApplicationConfigProperties applicationConfigProperties;
    private final JpaParentRepository jpaParentRepository;


    public ParentRepositoryImpl(ApplicationConfigProperties applicationConfigProperties,final JpaParentRepository jpaParentRepository){
        this.applicationConfigProperties = applicationConfigProperties;
        this.jpaParentRepository = jpaParentRepository;
    }

    @Override
    public Optional<ParentLogs> findByStudentId(Object studentId){
        ParentLogs parentLogs;
        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()){
            case "postgresql" -> {
                Optional<ParentJpa> resultJpa = jpaParentRepository.findByStudentId((UUID) studentId);
               if (resultJpa.isPresent()){
                   parentLogs = new ParentLogs(
                           resultJpa.get().getFatherName(),
                           resultJpa.get().getFatherDateBirth(),
                           resultJpa.get().getFatherNik(),
                           resultJpa.get().getFatherEducation(),
                           resultJpa.get().getFatherJob(),
                           resultJpa.get().getFatherCitizen(),
                           resultJpa.get().getFatherIncome(),
                           resultJpa.get().getFatherAddress(),
                           resultJpa.get().getFatherPhone(),
                           resultJpa.get().getMotherName(),
                           resultJpa.get().getMotherDateBirth(),
                           resultJpa.get().getMotherNik(),
                           resultJpa.get().getMotherEducation(),
                           resultJpa.get().getMotherCitizen(),
                           resultJpa.get().getMotherIncome(),
                           resultJpa.get().getMotherAddress(),
                           resultJpa.get().getMotherPhone()
                   );
               }else {
                   return Optional.empty();
               }
            }
            default -> throw new IllegalArgumentException("Unsupported database: " + applicationConfigProperties.getDatabaseDefault());
        }
        return Optional.of(parentLogs);
    };
}
