package org.school.backend.domain.gateaway;

import org.school.backend.domain.model.StudentModel;

import java.util.List;
import java.util.Optional;

public interface StudentLogGateaway {
    Optional<List<StudentModel>> findAll(int page, int rpp);
    Optional<StudentModel> findById(Object id);
    void create(StudentModel record);
}
