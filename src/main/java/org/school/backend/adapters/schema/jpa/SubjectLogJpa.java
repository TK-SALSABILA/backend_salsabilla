package org.school.backend.adapters.schema.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "SUBJECT", schema = "public")
public class SubjectLogJpa {
    @Id
    @Column(name = "ID", columnDefinition = "uuid", updatable = false, nullable = false)
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "SUBJECT_NAME")
    private String subjectName;

    @Column(name = "SUBJECT_CODE")
    private String subjectCode;

    @Column(name = "GRADE_LEVEL")
    private String gradeLevel;

    @Column(name = "IS_MANDATORY")
    private Boolean isMandatory;

    @Column(name = "DESCRIPTION")
    private String description;


    public SubjectLogJpa(
            String subjectName,
            String subjectCode,
            String gradeLevel,
            Boolean isMandatory,
            String description
    ) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.gradeLevel = gradeLevel;
        this.isMandatory = isMandatory;
        this.description = description;
    }
}
