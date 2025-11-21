package org.school.backend.adapters.schema.jpa;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACTIVITY_STUDENT_PARTICIPANT", schema = "public")
public class ActivityStudentSummaryJpa {

    @Id
    @Column(name = "ID", columnDefinition = "uuid")
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "ACTIVITY_ID", nullable = false)
    private UUID activityId;

    @Column(name = "STUDENT_ID", nullable = false)
    private UUID studentId;

    @Column(name = "STUDENT_NAME", nullable = false)
    private String studentName;

    @Column(name = "STUDENT_NIS")
    private String studentNis;

    @Column(name = "GRADE_ID", nullable = false)
    private UUID gradeId;

    @Column(name = "GRADE_NAME", nullable = false)
    private String gradeName;

    @Column(name = "AMOUNT_REQUIRED")
    private Integer amountRequired;

    @Column(name = "AMOUNT_PAID")
    private Integer amountPaid;

    @Column(name = "PAYMENT_STATUS")
    private String paymentStatus;

    @Column(name = "LAST_PAYMENT_DATE")
    private LocalDateTime lastPaymentDate;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public ActivityStudentSummaryJpa(
            UUID activityId,
            UUID studentId,
            String studentName,
            String studentNis,
            UUID gradeId,
            String gradeName,
            Integer amountRequired,
            Integer amountPaid,
            String paymentStatus,
            LocalDateTime lastPaymentDate
    ){
        this.activityId = activityId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentNis = studentNis;
        this.gradeId = gradeId;
        this.gradeName = gradeName;
        this.amountRequired = amountRequired;
        this.amountPaid = amountPaid;
        this.paymentStatus = paymentStatus;
        this.lastPaymentDate= lastPaymentDate;
    }
}
