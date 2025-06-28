package org.school.backend.adapters.datasources.repository;

import org.school.backend.adapters.dto.StudentDetails;
import org.school.backend.adapters.dto.StudentLogs;

import java.util.List;
import java.util.Optional;

public interface StudentLogsRepository {

    List<StudentLogs> findAll();
    List<StudentLogs> findAll(int rpp,int page);
    Optional<StudentDetails> findById(Object id);
}
