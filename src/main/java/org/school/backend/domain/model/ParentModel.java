package org.school.backend.domain.model;

import java.io.Serializable;
import java.time.LocalDate;

public record ParentModel(
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
) implements Serializable {
}
