package org.school.backend.adapters.datasources.repository.impl;

import jakarta.transaction.Transactional;
import org.school.backend.adapters.configuration.ApplicationConfigProperties;
import org.school.backend.adapters.datasources.repository.ActivityRepository;
import org.school.backend.adapters.datasources.repository.JpaActivityRepository;
import org.school.backend.adapters.dto.ActivityRequest;
import org.school.backend.adapters.dto.ActivityResponse;
import org.school.backend.adapters.mapper.ActivityMapper;
import org.school.backend.adapters.schema.jpa.ActivityJpa;
import org.school.backend.domain.model.ActivityModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ActivityRepositoryImpl implements ActivityRepository {

    final ApplicationConfigProperties applicationConfigProperties;
    final JpaActivityRepository jpaActivityRepository;

    public ActivityRepositoryImpl(
            ApplicationConfigProperties applicationConfigProperties,
            JpaActivityRepository jpaActivityRepository
    ){
        this.applicationConfigProperties = applicationConfigProperties;
        this.jpaActivityRepository = jpaActivityRepository;
    }
    @Override
    public List<ActivityResponse> getActivityStudent(int page, int rpp) {
        List<ActivityResponse> result = new ArrayList<>();

        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()){
            case "postgresql" -> {
                result.addAll(
                        ActivityMapper.toActivityResponses(
                                jpaActivityRepository.findAllWithClassParticipantsNative()
                        )
                );

            }
            default -> throw new IllegalArgumentException(applicationConfigProperties.getDatabaseDefault().toLowerCase());


        }
        return result;
    }

    @Override
    public ActivityModel createActivity(ActivityRequest request) {
        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()){
            case "postgresql" -> {
                ActivityJpa data =  new ActivityJpa(
                        request.getActivityName(),
                        request.getActivityDate(),
                        request.getDescription(),
                        request.getTotalFundsRequired(),
                        0,
                        request.getIsActive()
                );
                ActivityJpa savedData = jpaActivityRepository.save(data);
                return new ActivityModel(
                         savedData.getId(),
                         savedData.getActivityName(),
                         savedData.getActivityDate(),
                         savedData.getDescription(),
                         savedData.getTotalFundRequired(),
                         savedData.getTotalFundRaised(),
                         savedData.getIsActive(),
                        null
                );
            }
            default -> throw new IllegalArgumentException(applicationConfigProperties.getDatabaseDefault().toLowerCase());
        }
    }

    @Override
    public void updateActivity(UUID activityId, int amount) {
        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()) {
            case "postgresql" -> {
                ActivityJpa data = jpaActivityRepository.findById(activityId)
                        .orElseThrow(() -> new IllegalArgumentException("Activity not found"));

                data.setTotalFundRaised(data.getTotalFundRaised() + amount);
                jpaActivityRepository.save(data);
            }
            default -> throw new UnsupportedOperationException("Database not supported");
        }
    }
}
