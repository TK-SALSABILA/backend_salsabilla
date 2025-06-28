package org.school.backend.domain.model;

public record AcademicScore(
        Integer id,
        StudentModel student,
        SemesterModel semester,
        SubjectModel subject,
        Integer value
) {
}
