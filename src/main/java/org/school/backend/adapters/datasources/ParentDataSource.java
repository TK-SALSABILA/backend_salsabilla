package org.school.backend.adapters.datasources;

import org.school.backend.adapters.dto.ParentLogs;

import java.util.Optional;

public interface ParentDataSource {
    Optional<ParentLogs> findByStudentId(Object id);
}
