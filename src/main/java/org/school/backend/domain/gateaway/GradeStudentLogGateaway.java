package org.school.backend.domain.gateaway;

import org.school.backend.domain.model.GradeStudentModel;
import org.school.backend.domain.model.StudentParticipantModel;

import java.util.List;
import java.util.UUID;

public interface GradeStudentLogGateaway {
    GradeStudentModel findStudentByClass(Object classId);
    List<StudentParticipantModel> findActivityStudentParticipant(List<UUID> gradeId);
}
