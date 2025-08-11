package org.school.backend.adapters.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TuitionFeeLogReq {
    UUID id;
    UUID studentId;
    String month;
    String paymentType;
    String transactionType;
    Integer amount;
    String status;
    LocalDateTime transactionDate;
}