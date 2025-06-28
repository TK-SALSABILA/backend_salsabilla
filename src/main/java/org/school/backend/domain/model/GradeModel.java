package org.school.backend.domain.model;

import java.io.Serializable;

public record GradeModel(
    String gradeLevel,
    String academicYear
) implements Serializable {
}
