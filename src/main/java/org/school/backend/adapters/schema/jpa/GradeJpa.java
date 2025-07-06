package org.school.backend.adapters.schema.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.school.backend.application.dto.GradeDto;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GRADE", schema="public")
public class GradeJpa {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "GRADE_LEVEL")
    private String gradeLevel;

    @Column(name = "ACADEMIC_YEAR")
    private String academicYear;

    @Column(name = "STUDENT_ID")
    private UUID studentId;

    public GradeJpa(
            String gradeLevel,
            String academicYear,
            UUID studentId
    ){
        this.academicYear = academicYear;
        this.gradeLevel = gradeLevel;
        this.studentId = studentId;
    }


}
