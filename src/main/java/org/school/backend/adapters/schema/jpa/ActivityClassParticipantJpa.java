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
@Table(name = "ACTIVITY_CLASS_PARTICIPANT", schema="public")
public class ActivityClassParticipantJpa {
    @Id
    @Column(name = "ID",columnDefinition = "uuid", updatable = false,nullable = false)
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "ACTIVITY_ID")
    private UUID activityId;

    @Column(name = "GRADE_ID")
    private UUID gradeId;

    @Column(name = "GRADE_NAME")
    private String gradeName;

    public ActivityClassParticipantJpa(

            UUID activityId,
            UUID gradeId,
            String gradeName
    ){

        this.activityId = activityId;
        this.gradeId = gradeId;
        this.gradeName = gradeName;
    }
}
