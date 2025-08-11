package org.school.backend.domain.gateaway;

import org.school.backend.domain.model.GradeStudentModel;

public interface GradeStudentLogGateaway {
    GradeStudentModel findStudentByClass(Object classId);
}
