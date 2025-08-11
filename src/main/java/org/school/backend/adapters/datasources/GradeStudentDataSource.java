package org.school.backend.adapters.datasources;

import org.school.backend.domain.model.GradeStudentModel;

public interface GradeStudentDataSource {
    GradeStudentModel findStudentByClass(Object classId);
}
