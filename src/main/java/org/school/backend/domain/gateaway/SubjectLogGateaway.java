package org.school.backend.domain.gateaway;

import org.school.backend.domain.model.SubjectModel;

import java.util.List;
import java.util.Optional;

public interface SubjectLogGateaway {
    Optional<List<SubjectModel>> findAll (int page, int rpp);
    Optional<SubjectModel> findById(Object id);
    void deleteById(Object id);
    void update(Object id,SubjectModel record);
    void create(SubjectModel record);
}
