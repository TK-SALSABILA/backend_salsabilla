package org.school.backend.adapters.datasources;

import org.school.backend.adapters.dto.StudentDetails;
import org.school.backend.adapters.dto.StudentLogs;
import org.school.backend.adapters.dto.StudentRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentDataSource {
    List<StudentLogs> findAll(int rpp,int page);
    List<StudentLogs> getStudent(int page, int rpp,String studentName, UUID classId);
    Optional<StudentDetails> findById(Object id);
    void create(StudentRequest record);
    void update(Object id, StudentDetails record);

}
