package org.school.backend.adapters.mapper;

import org.school.backend.adapters.dto.ActivityPaymentRequest;
import org.school.backend.domain.model.ActivityPaymentModel;

public interface ActivityPaymentMapper {

    static ActivityPaymentRequest covertEntityToModel(ActivityPaymentModel  domain){
      return ActivityPaymentRequest.builder()
              .activityId(domain.activityId())
              .studentId(domain.studentId())
              .paymentType(domain.paymentType())
              .paymentDate(domain.paymentDate())
              .amount(domain.amount())
              .description(domain.description())
              .build();
    };
}
