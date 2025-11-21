package org.school.backend.adapters.datasources.repository;

import org.school.backend.domain.model.GradeStudentModel;
import org.school.backend.domain.model.StudentParticipantModel;

import java.util.List;
import java.util.UUID;

public interface GradeStudentRepository {
    GradeStudentModel findStudentByClass(Object classId);
    List<StudentParticipantModel> findActivityStudentParticipant(List<UUID> gradeId);
}
