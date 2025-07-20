package org.school.backend.domain.gateaway;

import org.school.backend.domain.model.StudentGradeModel;

import java.util.Optional;

public interface StudentGradeLogGateAway {
    Optional<StudentGradeModel> findByStudentId(Object id);
}
