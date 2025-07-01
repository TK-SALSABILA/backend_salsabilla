package org.school.backend.application.dto;

import org.school.backend.domain.model.ParentModel;

import java.time.LocalDate;

public record ParentDto(
        Integer id,
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
        String postalCode
) {
    public ParentDto(ParentModel record){
        this(
                record.id(),
                record.fatherName(),
                record.fatherDateBirth(),
                record.fatherNik(),
                record.fatherEducation(),
                record.fatherJob(),
                record.motherName(),
                record.motherDateBirth(),
                record.motherNik(),
                record.motherEducation(),
                record.phone(),
                record.fullAddress(),
                record.postalCode()
        );
    }
}
