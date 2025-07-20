package org.school.backend.domain.gateaway;

import org.school.backend.domain.model.GradeModel;

import java.util.List;
import java.util.Optional;

public interface GradeLogGateaway {
    Optional<List<GradeModel>> findAll(int page, int rpp);
    void create(GradeModel record);
}
