package org.school.backend.adapters.datasources.repository.impl;

import org.school.backend.adapters.configuration.ApplicationConfigProperties;
import org.school.backend.adapters.datasources.repository.ActivityStudentParticipantRepository;
import org.school.backend.adapters.datasources.repository.JpaActivityStudentParticipantRepository;
import org.school.backend.adapters.dto.ActivityStudentParticipant;
import org.school.backend.adapters.schema.jpa.ActivityStudentSummaryJpa;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ActivityStudentParticipantRepositoryImpl implements ActivityStudentParticipantRepository {
    private final ApplicationConfigProperties applicationConfigProperties;
    private final JpaActivityStudentParticipantRepository jpaActivityStudentParticipantRepository;

    public ActivityStudentParticipantRepositoryImpl(
            ApplicationConfigProperties applicationConfigProperties,
            JpaActivityStudentParticipantRepository jpaActivityStudentParticipantRepository
    ){
        this.applicationConfigProperties = applicationConfigProperties;
        this.jpaActivityStudentParticipantRepository = jpaActivityStudentParticipantRepository;
    }
    @Override
    public List<ActivityStudentParticipant> getListStudent(UUID activityId, int page, int rpp) {
        List<ActivityStudentParticipant> result = new ArrayList<>();
        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase())
        {
            case "postgresql" -> jpaActivityStudentParticipantRepository.findByActivityId(activityId)
                    .forEach(entity -> result.add(new ActivityStudentParticipant(
                            entity.getId(),
                            entity.getActivityId(),
                            entity.getStudentId(),
                            entity.getGradeId(),
                            entity.getStudentName(),
                            null,
                            entity.getGradeName(),
                            entity.getAmountRequired(),
                            entity.getAmountPaid(),
                            entity.getPaymentStatus(),
                            entity.getLastPaymentDate()
                    )));
            default -> throw new IllegalArgumentException(applicationConfigProperties.getDatabaseDefault().toLowerCase());

        }
        return result;
    }

    @Override
    public void createActivityStudents(List<ActivityStudentParticipant> request) {
        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()){
            case "postgresql" -> {
                List<ActivityStudentSummaryJpa> entities = request.stream()
                        .map(req -> new ActivityStudentSummaryJpa(
                                req.getActivityId(),
                                req.getStudentId(),
                                req.getStudentName(),
                                req.getStudentNis(),
                                req.getGradeId(),
                                req.getGradeName(),
                                req.getAmountRequired(),
                                req.getAmountPaid(),
                                req.getPaymentStatus(),
                                req.getLastPaymentDate()
                        )).collect(Collectors.toList());

                jpaActivityStudentParticipantRepository.saveAll(entities);
            }
            default -> throw new IllegalArgumentException(applicationConfigProperties.getDatabaseDefault().toLowerCase());
        }
    }
}
