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
@Table(name = "OPERATIONAL_FEE", schema="public")
public class OptFeeJpa {
    @Id
    @Column(name = "ID",columnDefinition = "uuid", updatable = false,nullable = false)
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "PAYMENT_TYPE")
    private String paymentType;

    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;

    @Column(name = "TRANSACTION_DATE")
    private LocalDateTime transactionDate;

    @Column(name = "AMOUNT")
    private Integer amount;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "STUDENT_ID")
    private UUID studentId;

    public OptFeeJpa(
            String paymentType,
            String transactionType,
            LocalDateTime transactionDate,
            Integer amount,
            String description,
            String status,
            UUID studentId
    ){
        this.paymentType = paymentType;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.studentId =studentId;
    }
}
