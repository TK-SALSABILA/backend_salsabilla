package org.school.backend.adapters.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TuitionFeeLogs {
    UUID id;
    UUID studentId;
    String month;
    Integer amount;
    String status;
    String paymentType;
    LocalDateTime transactionDate;
}
