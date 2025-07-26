package org.school.backend.adapters.dto;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentGrade {
    String academicYear;
    Boolean isCurrent;
    GradeLogs gradeLog;
}
