package org.school.backend.adapters.datasources;

import org.school.backend.adapters.dto.SubjectLogs;
import org.school.backend.domain.model.SubjectModel;

import java.util.List;
import java.util.Optional;

public interface SubjectDataSource {
    List<SubjectLogs> findAll(int page, int rpp);
    List<SubjectLogs> getSubject(int page, int rpp, String subject,Boolean isMandatory);
    Optional<SubjectLogs> findById(Object id);
    void deleteById(Object id);
    void update(Object id, SubjectLogs record);
    void create(SubjectLogs record);
}
