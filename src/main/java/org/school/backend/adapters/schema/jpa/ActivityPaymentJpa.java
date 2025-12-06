package org.school.backend.adapters.schema.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ACTIVITY_PAYMENT", schema = "public")
public class ActivityPaymentJpa {

    @Id
    @Column(name = "ID", columnDefinition = "uuid", updatable = false, nullable = false)
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "ACTIVITY_ID", nullable = false)
    private UUID activityId;

    @Column(name = "STUDENT_ID", nullable = false)
    private UUID studentId;

    @Column(name = "AMOUNT", nullable = false)
    private Integer amount;

    @Column(name = "PAYMENT_TYPE", nullable = false)
    private String paymentType;

    @Column(name = "PAYMENT_DATE", nullable = false)
    private LocalDateTime paymentDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public ActivityPaymentJpa(
             UUID activityId,
             UUID studentId,
             Integer amount,
             String paymentType,
             LocalDateTime paymentDate,
             String description
    ){
        this.activityId = activityId;
        this.studentId = studentId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.paymentDate = paymentDate;
        this.description = description;
    }
}