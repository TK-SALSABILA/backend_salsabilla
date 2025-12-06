package org.school.backend.adapters.datasources.repository.impl;

import org.school.backend.adapters.configuration.ApplicationConfigProperties;
import org.school.backend.adapters.datasources.repository.ActivityPaymentRepository;
import org.school.backend.adapters.datasources.repository.JpaActivityPaymentRepository;
import org.school.backend.adapters.dto.ActivityPaymentRequest;
import org.school.backend.adapters.schema.jpa.ActivityPaymentJpa;
import org.springframework.stereotype.Component;

@Component
public class ActivityPaymentRepositoryImpl implements ActivityPaymentRepository {
    private ApplicationConfigProperties applicationConfigProperties;
    private JpaActivityPaymentRepository jpaActivityPaymentRepository;

    public  ActivityPaymentRepositoryImpl(ApplicationConfigProperties applicationConfigProperties, JpaActivityPaymentRepository jpaActivityPaymentRepository){
        this.applicationConfigProperties = applicationConfigProperties;
        this.jpaActivityPaymentRepository = jpaActivityPaymentRepository;

    }
    @Override
    public void createPayment(ActivityPaymentRequest request) {
        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()){
            case "postgresql" -> {
                ActivityPaymentJpa data = new ActivityPaymentJpa(
                        request.getActivityId(),
                        request.getStudentId(),
                        request.getAmount(),
                        request.getPaymentType(),
                        request.getPaymentDate(),
                        request.getDescription()
                );
                jpaActivityPaymentRepository.save(data);
            }
        }
    }
}
