package org.school.backend.domain.gateaway;

import org.school.backend.domain.model.StudentModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentLogGateaway {
    Optional<List<StudentModel>> findAll(int page, int rpp);
    Optional<List<StudentModel>> getStudent(int page, int rpp, String student,UUID classId);
    Optional<StudentModel> findById(Object id);
    void create(StudentModel record);
    void update(Object id,StudentModel record);

}
