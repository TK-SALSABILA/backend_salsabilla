package org.school.backend.application.dto;

import org.school.backend.domain.model.ParentModel;

import java.time.LocalDate;

import static org.school.backend.application.utils.DateTimeFormatterConfig.toStringFormat;

public record ParentDto(
        Integer id,
        String fatherName,
        String fatherDateBirth,
        String fatherNik,
        String fatherEducation,
        String fatherJob,
        String fatherCitizen,
        Long fatherIncome,
        String fatherAddress,
        String fatherPhone,
        String motherName,
        String motherDateBirth,
        String motherNik,
        String motherEducation,
        String motherCitizen,
        Long motherIncome,
        String motherAddress,
        String motherPhone
) {
    public ParentDto(ParentModel record){
        this(
                record.id(),
                record.fatherName(),
                toStringFormat(record.fatherDateBirth()),
                record.fatherNik(),
                record.fatherEducation(),
                record.fatherJob(),
                record.fatherCitizen(),
                record.fatherIncome(),
                record.fatherAddress(),
                record.fatherPhone(),
                record.motherName(),
                toStringFormat(record.motherDateBirth()),
                record.motherNik(),
                record.motherEducation(),
                record.motherCitizen(),
                record.motherIncome(),
                record.motherAddress(),
                record.motherPhone()
        );
    }
}
