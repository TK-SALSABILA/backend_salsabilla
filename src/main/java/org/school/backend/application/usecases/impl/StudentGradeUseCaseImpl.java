package org.school.backend.application.usecases.impl;

import org.school.backend.application.dto.StudentGradeDto;
import org.school.backend.application.exception.StudentGradeDataNotFoundException;
import org.school.backend.application.mappers.StudentGradeMapper;
import org.school.backend.application.usecases.StudentGradeUseCase;
import org.school.backend.domain.gateaway.StudentGradeLogGateAway;
import org.school.backend.domain.model.StudentGradeModel;

import java.util.Optional;

public class StudentGradeUseCaseImpl implements StudentGradeUseCase {

    private final StudentGradeLogGateAway gradeLogGateAway;

    public StudentGradeUseCaseImpl(final StudentGradeLogGateAway repository){
        this.gradeLogGateAway = repository;

    }

    @Override
    public Optional<StudentGradeDto> findByStudentId(Integer id){
        Optional<StudentGradeModel> gradeModel = Optional.ofNullable(this.gradeLogGateAway.findByStudentId(id).orElseThrow(StudentGradeDataNotFoundException::new));
        return Optional.of(StudentGradeMapper.toDto(gradeModel.get()));
    }
}
