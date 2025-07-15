package org.school.backend.domain.model;

import java.io.Serializable;
import java.util.UUID;

public record StudentGradeModel(
    UUID id,
    String academicYear,
    Boolean isCurrent,
    GradeModel gradeLog
) implements Serializable {

    public StudentGradeModel(
            String academicYear,
            Boolean isCurrent,
            GradeModel gradeLog
    ){
        this(
                null,
                academicYear,
                isCurrent,
                gradeLog
        );
    }
}
