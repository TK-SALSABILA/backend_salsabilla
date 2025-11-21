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
@Table(name = "ACTIVITY", schema="public")
public class ActivityJpa {
    @Id
    @Column(name = "ID",columnDefinition = "uuid", updatable = false,nullable = false)
    @GeneratedValue
    @UuidGenerator
    private UUID id;

    @Column(name = "ACTIVITY_NAME")
    private String activityName;

    @Column(name = "ACTIVITY_DATE")
    private LocalDateTime activityDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TOTAL_FUND_REQUIRED")
    private Integer totalFundRequired;

    @Column(name = "TOTAL_FUND_RAISED")
    private Integer totalFundRaised;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    public ActivityJpa(
            String activityName,
            LocalDateTime activityDate,
            String description,
            Integer totalFundRequired,
            Integer totalFundRaised,
            Boolean isActive

    ){
        this.activityName = activityName;
        this.activityDate = activityDate;
        this.description = description;
        this.totalFundRequired = totalFundRequired;
        this.totalFundRaised = totalFundRaised;
        this.isActive = isActive;
    }

}
