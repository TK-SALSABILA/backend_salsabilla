package org.school.backend.adapters.datasources.repository;

import org.school.backend.domain.model.GradeStudentModel;

public interface GradeStudentRepository {
    GradeStudentModel findStudentByClass(Object classId);
}
