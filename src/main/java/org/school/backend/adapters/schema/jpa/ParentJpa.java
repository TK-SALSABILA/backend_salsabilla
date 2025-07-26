package org.school.backend.adapters.schema.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PARENT", schema="public")
public class ParentJpa {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "FATHER_NAME")
    private String fatherName;

    @Column(name = "FATHER_DATE_BIRTH")
    private LocalDateTime fatherDateBirth;

    @Column(name = "FATHER_NIK")
    private String fatherNik;

    @Column(name = "FATHER_EDUCATION")
    private String fatherEducation;

    @Column(name = "FATHER_JOB")
    private String fatherJob;

    @Column(name = "FATHER_CITIZEN")
    private String fatherCitizen;

    @Column(name = "FATHER_INCOME")
    private Long fatherIncome;

    @Column(name = "FATHER_ADDRESS")
    private String fatherAddress;

    @Column(name = "FATHER_PHONE")
    private String fatherPhone;

    @Column(name = "MOTHER_NAME")
    private String motherName;

    @Column(name = "MOTHER_DATE_BIRTH")
    private LocalDateTime motherDateBirth;

    @Column(name = "MOTHER_NIK")
    private String motherNik;

    @Column(name = "MOTHER_EDUCATION")
    private String motherEducation;

    @Column(name = "MOTHER_CITIZEN")
    private String motherCitizen;

    @Column(name = "MOTHER_INCOME")
    private Long motherIncome;

    @Column(name = "MOTHER_ADDRESS")
    private String motherAddress;

    @Column(name = "MOTHER_PHONE")
    private String motherPhone;

    @Column(name = "STUDENT_ID")
    private UUID studentId;

    public ParentJpa(
            String fatherName,
            LocalDateTime fatherDateBirth,
            String fatherNik,
            String fatherEducation,
            String fatherJob,
            String fatherCitizen,
            Long fatherIncome,
            String fatherAddress,
            String fatherPhone,
            String motherName,
            LocalDateTime motherDateBirth,
            String motherNik,
            String motherEducation,
            String motherCitizen,
            Long motherIncome,
            String motherAddress,
            String motherPhone,
            UUID studentId
    ) {
        this.fatherName = fatherName;
        this.fatherDateBirth = fatherDateBirth;
        this.fatherNik = fatherNik;
        this.fatherEducation = fatherEducation;
        this.fatherJob = fatherJob;
        this.fatherCitizen =fatherCitizen;
        this.fatherIncome = fatherIncome;
        this.fatherAddress = fatherAddress;
        this.fatherPhone = fatherPhone;
        this.motherName = motherName;
        this.motherDateBirth = motherDateBirth;
        this.motherNik = motherNik;
        this.motherEducation = motherEducation;
        this.motherCitizen = motherCitizen;
        this.motherIncome = motherIncome;
        this.motherAddress = motherAddress;
        this.motherPhone = motherPhone;
        this.studentId = studentId;
    }

}

