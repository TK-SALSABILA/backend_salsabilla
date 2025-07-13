package org.school.backend.application.dto;

import org.school.backend.application.mappers.GradeMapper;
import org.school.backend.domain.model.StudentGradeModel;

import java.io.Serializable;

public record StudentGradeDto(
        String academicYear,
        Boolean isCurrent,
        GradeDto gradeLog
) implements Serializable {

    public StudentGradeDto(StudentGradeModel gradeRecordModel) {
        this(
                gradeRecordModel.academicYear(),
                gradeRecordModel.isCurrent(),
                GradeMapper.toDto(gradeRecordModel.gradeLog())
        );
    }
}