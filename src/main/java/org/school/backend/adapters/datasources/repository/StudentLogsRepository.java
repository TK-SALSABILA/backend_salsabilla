package org.school.backend.adapters.datasources.repository;

import org.school.backend.adapters.dto.StudentDetails;
import org.school.backend.adapters.dto.StudentLogs;
import org.school.backend.adapters.dto.StudentRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface StudentLogsRepository {

    List<StudentLogs> findAll();
    List<StudentLogs> findAll(int rpp,int page);
    List<StudentLogs> getStudent(int page, int rpp,String StudentName,UUID classId);
    Optional<StudentDetails> findById(Object id);
    void create(StudentRequest record);
    void update(Object id, StudentDetails record);
    List<StudentLogs> findAllStudentId(Set<UUID> id);
}
