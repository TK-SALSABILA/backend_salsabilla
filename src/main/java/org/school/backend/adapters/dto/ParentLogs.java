package org.school.backend.adapters.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParentLogs {
    String fatherName;
    LocalDateTime fatherDateBirth;
    String fatherNik;
    String fatherEducation;
    String fatherJob;
    String fatherCitizen;
    Long fatherIncome;
    String fatherAddress;
    String fatherPhone;
    String motherName;
    LocalDateTime motherDateBirth;
    String motherNik;
    String motherEducation;
    String motherCitizen;
    Long motherIncome;
    String motherAddress;
    String motherPhone;
}
