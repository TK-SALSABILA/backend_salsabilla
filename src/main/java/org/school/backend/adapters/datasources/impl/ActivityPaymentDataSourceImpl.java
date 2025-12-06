package org.school.backend.adapters.datasources.impl;

import org.school.backend.adapters.datasources.ActivityPaymentDataSource;
import org.school.backend.adapters.datasources.repository.ActivityPaymentRepository;
import org.school.backend.adapters.dto.ActivityPaymentRequest;
import org.springframework.stereotype.Component;

@Component
public class ActivityPaymentDataSourceImpl implements ActivityPaymentDataSource {
    private ActivityPaymentRepository repository;

    public ActivityPaymentDataSourceImpl(ActivityPaymentRepository repository){
        this.repository = repository;
    }

    @Override
    public void createPayment(ActivityPaymentRequest request) {
        repository.createPayment(request);
    }
}
