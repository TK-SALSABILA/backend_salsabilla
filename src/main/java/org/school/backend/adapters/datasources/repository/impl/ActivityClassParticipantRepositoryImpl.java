package org.school.backend.adapters.datasources.repository.impl;

import org.school.backend.adapters.configuration.ApplicationConfigProperties;
import org.school.backend.adapters.datasources.repository.ActivityClassParticipantRepository;
import org.school.backend.adapters.datasources.repository.JpaActivityClassParticipantRepository;
import org.school.backend.adapters.dto.ActivityClassParticipant;
import org.school.backend.adapters.schema.jpa.ActivityClassParticipantJpa;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ActivityClassParticipantRepositoryImpl implements ActivityClassParticipantRepository {
    private final ApplicationConfigProperties applicationConfigProperties;
    private final JpaActivityClassParticipantRepository jpaActivityClassParticipantRepository;

    public ActivityClassParticipantRepositoryImpl(
            ApplicationConfigProperties  applicationConfigProperties,
            JpaActivityClassParticipantRepository jpaActivityClassParticipantRepository
    ){
        this.applicationConfigProperties = applicationConfigProperties;
        this.jpaActivityClassParticipantRepository = jpaActivityClassParticipantRepository;
    }

    @Override
    public void create(List<ActivityClassParticipant> request) {
        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()){
            case "postgresql" -> {
                List<ActivityClassParticipantJpa> data = request.stream()
                        .map(req -> new ActivityClassParticipantJpa(
                                req.getActivityId(),
                                req.getGradeId(),
                                req.getGradeName()
                        ))
                        .toList();
                jpaActivityClassParticipantRepository.saveAll(data);
            }
            default -> throw new IllegalArgumentException(applicationConfigProperties.getDatabaseDefault().toLowerCase());
        }
    }
}
