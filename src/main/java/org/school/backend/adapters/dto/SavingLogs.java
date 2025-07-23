package org.school.backend.adapters.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SavingLogs {
    UUID id;
    UUID studentId;
    String studentName;
    String paymentType;
    Integer totalAmount;
    LocalDateTime transactionDate;
    String description;
}
