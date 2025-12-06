package org.school.backend.adapters.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityPaymentRequest {
    private UUID activityId;
    private UUID studentId;
    private Integer amount;
    private String paymentType;
    private LocalDateTime paymentDate;
    private String description;
    private LocalDateTime createdAt;
}
