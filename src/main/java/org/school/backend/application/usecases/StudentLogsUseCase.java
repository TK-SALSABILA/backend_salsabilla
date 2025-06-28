package org.school.backend.application.usecases;

import org.school.backend.application.dto.response.StudentsLogsOutputDto;

import java.util.List;
import java.util.Optional;

public interface StudentLogsUseCase {
    Optional<List<StudentsLogsOutputDto>> findAll(int rpp,int page);
    Optional<StudentsLogsOutputDto> findById(Integer id);
}