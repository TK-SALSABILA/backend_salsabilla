package org.school.backend.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ParentModel(
        Integer id,
        String fatherName,
        LocalDateTime fatherDateBirth,
        String fatherNik,
        String fatherEducation,
        String fatherJob,
        String fatherCitizen,
        Long fatherIncome,
        String fatherAddress,
        String fatherPhone,
        String motherName,
        LocalDateTime motherDateBirth,
        String motherNik,
        String motherEducation,
        String motherCitizen,
        Long motherIncome,
        String motherAddress,
        String motherPhone
) implements Serializable {

    public ParentModel(
            String fatherName,
            LocalDateTime fatherDateBirth,
            String fatherNik,
            String fatherEducation,
            String fatherJob,
            String fatherCitizen,
            Long fatherIncome,
            String fatherAddress,
            String fatherPhone,
            String motherName,
            LocalDateTime motherDateBirth,
            String motherNik,
            String motherEducation,
            String motherCitizen,
            Long motherIncome,
            String motherAddress,
            String motherPhone
    ){
        this(
                null,
                fatherName,
                fatherDateBirth,
                fatherNik,
                fatherEducation,
                fatherJob,
                fatherCitizen,
                fatherIncome,
                fatherAddress,
                fatherPhone,
                motherName,
                motherDateBirth,
                motherNik,
                motherEducation,
                motherCitizen,
                motherIncome,
                motherAddress,
                motherPhone

        );
    }
}
