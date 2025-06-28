package org.school.backend.adapters.datasources;

import org.school.backend.adapters.dto.StudentDetails;
import org.school.backend.adapters.dto.StudentLogs;

import java.util.List;
import java.util.Optional;

public interface StudentDataSource {

    List<StudentLogs> findAll(int rpp,int page);
    Optional<StudentDetails> findById(Object id);
}
