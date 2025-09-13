package org.school.backend.adapters.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OptFeeLogs {
    UUID id;
    UUID studentId;
    String paymentType;
    Integer amount;
    String status;
    LocalDateTime transactionDate;
    String description;
}
