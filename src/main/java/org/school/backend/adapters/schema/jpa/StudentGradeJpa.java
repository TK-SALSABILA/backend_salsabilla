package org.school.backend.adapters.schema.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.school.backend.domain.model.GradeModel;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STUDENT_GRADE", schema="public")
public class StudentGradeJpa {
    @Id
    @Column(name = "ID",columnDefinition = "uuid", updatable = false,nullable = false)
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "ACADEMIC_YEAR")
    private String academicYear;

    @Column(name = "IS_CURRENT")
    private Boolean isCurrent;

    @Column(name = "STUDENT_ID")
    private UUID studentId;

    @Column(name = "GRADE_ID")
    private UUID gradeId;

    public StudentGradeJpa (
        String academicYear,
        Boolean isCurrent,
        UUID studentId,
        UUID gradeId
    ){
        this.academicYear =academicYear;
        this.isCurrent =isCurrent;
        this.studentId = studentId;
        this.gradeId = gradeId;
    }

}
