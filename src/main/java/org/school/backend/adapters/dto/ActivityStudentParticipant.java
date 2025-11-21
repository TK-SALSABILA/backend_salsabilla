package org.school.backend.adapters.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityStudentParticipant {
    private UUID id;
    private UUID activityId;
    private UUID studentId;
    private UUID gradeId;
    private String studentName;
    private String studentNis;
    private String gradeName;
    private Integer amountRequired;
    private Integer amountPaid;
    private String paymentStatus;
    private LocalDateTime lastPaymentDate;
}
