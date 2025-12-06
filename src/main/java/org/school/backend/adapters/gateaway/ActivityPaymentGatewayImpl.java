package org.school.backend.adapters.gateaway;

import org.school.backend.adapters.datasources.ActivityPaymentDataSource;
import org.school.backend.domain.gateaway.ActivityPaymentGateway;
import org.school.backend.domain.model.ActivityPaymentModel;
import org.springframework.stereotype.Component;

import static org.school.backend.adapters.mapper.ActivityPaymentMapper.covertEntityToModel;

@Component
public class ActivityPaymentGatewayImpl implements ActivityPaymentGateway {

    private ActivityPaymentDataSource dataSource;

    public ActivityPaymentGatewayImpl(ActivityPaymentDataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void createPayment(ActivityPaymentModel request) {
        dataSource.createPayment(covertEntityToModel(request));
    }
}
