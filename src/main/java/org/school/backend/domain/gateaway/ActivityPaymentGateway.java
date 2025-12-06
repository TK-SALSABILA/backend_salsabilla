package org.school.backend.domain.gateaway;

import org.school.backend.domain.model.ActivityPaymentModel;

public interface ActivityPaymentGateway {
    void createPayment(ActivityPaymentModel request);
}
