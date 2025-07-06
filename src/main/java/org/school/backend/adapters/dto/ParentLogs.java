package org.school.backend.adapters.dto;

import lombok.*;

import java.time.LocalDate;
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
    String motherName;
    LocalDateTime motherDateBirth;
    String motherNik;
    String motherEducation;
    String motherCitizen;
    Long motherIncome;
    String phone;
    String fullAddress;
    String postalCode;
}
