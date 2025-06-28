package org.school.backend.application.dto;

import org.school.backend.domain.model.GradeModel;

import java.io.Serializable;

public record GradeDto(
        String gradeLevel,
        String academicYear
) implements Serializable {
    public GradeDto(GradeModel gradeRecordModel) {
        this(
                gradeRecordModel.gradeLevel(),
                gradeRecordModel.academicYear()
        );
    }
}
