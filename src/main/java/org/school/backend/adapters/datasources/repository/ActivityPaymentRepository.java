package org.school.backend.adapters.datasources.repository;

import org.school.backend.adapters.dto.ActivityPaymentRequest;

public interface ActivityPaymentRepository {
    void createPayment(ActivityPaymentRequest request);
}
