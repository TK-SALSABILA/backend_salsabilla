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
@Table(name = "GRADE", schema="public")
public class GradeJpa {

    @Id
    @Column(name = "ID",columnDefinition = "uuid", updatable = false,nullable = false)
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "GRADE_LEVEL")
    private String gradeLevel;

    public GradeJpa(
            String gradeLevel
    ){
        this.gradeLevel = gradeLevel;

    }


}
