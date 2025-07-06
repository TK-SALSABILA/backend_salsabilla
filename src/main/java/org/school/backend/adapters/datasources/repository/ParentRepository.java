package org.school.backend.adapters.datasources.repository;

import org.school.backend.adapters.dto.ParentLogs;

import java.util.Optional;

public interface ParentRepository {
    Optional<ParentLogs> findByStudentId(Object id);
}
