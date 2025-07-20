package org.school.backend.adapters.datasources;

import org.school.backend.adapters.dto.SubjectLogs;
import org.school.backend.domain.model.SubjectModel;

import java.util.List;
import java.util.Optional;

public interface SubjectDataSource {
    List<SubjectLogs> findAll(int page, int rpp);
    Optional<SubjectLogs> findById(Object id);
    void update(Object id, SubjectLogs record);
    void create(SubjectLogs record);
}
