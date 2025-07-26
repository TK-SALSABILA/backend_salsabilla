package org.school.backend.adapters.datasources.repository;

import org.school.backend.adapters.dto.SubjectLogs;

import java.util.List;
import java.util.Optional;;

public interface SubjectLogRepository {
    List<SubjectLogs> findAll(int page, int rpp);
    Optional<SubjectLogs> findById(Object id);
    void deleteById(Object id);
    void update(Object id,SubjectLogs record);
    void create(SubjectLogs record);
}
