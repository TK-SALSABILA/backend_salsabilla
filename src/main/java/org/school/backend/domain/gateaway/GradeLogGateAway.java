package org.school.backend.domain.gateaway;

import org.school.backend.domain.model.GradeModel;

import java.util.Optional;

public interface GradeLogGateAway {
    Optional<GradeModel> findByStudentId(Object id);
}
