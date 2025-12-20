package org.school.backend.adapters.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SavingLogReq {
    public UUID studentId;
    public UUID activityId;
    public String paymentType;
    public String transactionType;
    public Integer amount;
    public String description;
    public LocalDateTime transactionDate;
}
