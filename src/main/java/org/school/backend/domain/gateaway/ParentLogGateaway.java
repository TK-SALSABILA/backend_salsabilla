package org.school.backend.domain.gateaway;

import org.school.backend.domain.model.ParentModel;

import java.util.Optional;

public interface ParentLogGateaway {
    Optional<ParentModel> findByStudentId(Object id);
}
