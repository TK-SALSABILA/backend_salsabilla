package org.school.backend.adapters.datasources;

import org.school.backend.adapters.dto.ActivityPaymentRequest;

public interface ActivityPaymentDataSource {
    void createPayment(ActivityPaymentRequest request);
}
