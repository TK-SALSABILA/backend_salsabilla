package org.school.backend.adapters.schema.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    private  LocalDate fatherDateBirth;
    @Column(name = "FATHER_NIK")
    private String fatherNik;

    @Column(name = "FATHER_EDUCATION")
    private String fatherEducation;

    @Column(name = "FATHER_JOB")
    private String fatherJob;

    @Column(name = "MOTHER_NAME")
    private String motherName;

    @Column(name = "MOTHER_DATE_BIRTH")
    private LocalDate motherDateBirth;

    @Column(name = "MOTHER_NIK")
    private String motherNik;

    @Column(name = "MOTHER_EDUCATION")
    private String motherEducation;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "FULL_ADDRESS")
    private String fullAddress;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Column(name = "STUDENT_ID")
    private Integer studentId;

    public ParentJpa(
            String fatherName,
            LocalDate fatherDateBirth,
            String fatherNik,
            String fatherEducation,
            String fatherJob,
            String motherName,
            LocalDate motherDateBirth,
            String motherNik,
            String motherEducation,
            String phone,
            String fullAddress,
            String postalCode,
            Integer studentId
    ) {
        this.fatherName = fatherName;
        this.fatherDateBirth = fatherDateBirth;
        this.fatherNik = fatherNik;
        this.fatherEducation = fatherEducation;
        this.fatherJob = fatherJob;
        this.motherName = motherName;
        this.motherDateBirth = motherDateBirth;
        this.motherNik = motherNik;
        this.motherEducation = motherEducation;
        this.phone = phone;
        this.fullAddress = fullAddress;
        this.postalCode = postalCode;
        this.studentId = studentId;
    }
}

