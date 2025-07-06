package org.school.backend.application.usecases;

import org.school.backend.application.dto.StudentDetailsDto;
import org.school.backend.application.dto.request.StudentRequestDto;
import org.school.backend.application.dto.response.StudentsLogsOutputDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StudentLogsUseCase {
    Optional<List<StudentsLogsOutputDto>> findAll(int rpp,int page);
    Optional<StudentDetailsDto> findById(UUID id);
    void create(StudentRequestDto record);
}