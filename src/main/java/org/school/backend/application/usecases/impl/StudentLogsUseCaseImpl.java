package org.school.backend.application.usecases.impl;

import org.school.backend.application.dto.response.StudentsLogsOutputDto;
import org.school.backend.application.exception.StudentDataNotFoundException;
import org.school.backend.application.mappers.StudentLogsMapper;
import org.school.backend.application.usecases.StudentLogsUseCase;
import org.school.backend.domain.gateaway.StudentLogGateaway;
import org.school.backend.domain.model.StudentModel;

import java.util.List;
import java.util.Optional;

public class StudentLogsUseCaseImpl implements StudentLogsUseCase {

    private final StudentLogGateaway studentLogGateaway;

    public StudentLogsUseCaseImpl(final StudentLogGateaway repository){
        this.studentLogGateaway = repository;
    }

    @Override
    public Optional<List<StudentsLogsOutputDto>> findAll(int rpp, int page){
        Optional<List<StudentModel>> studentModels = Optional.ofNullable(this.studentLogGateaway.findAll(rpp,page)).orElseThrow(StudentDataNotFoundException::new);
        return Optional.of(StudentLogsMapper.toListDto(studentModels.get()));
    }

    @Override
    public Optional<StudentsLogsOutputDto> findById(Integer id){
        Optional<StudentModel> studentModel = Optional.ofNullable(this.studentLogGateaway.findById(id)).orElseThrow(StudentDataNotFoundException::new);
        return Optional.of(StudentLogsMapper.toDto(studentModel.get()));
    }
}
