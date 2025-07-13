package org.school.backend.application.usecases.impl;

import org.school.backend.application.dto.StudentGradeDto;
import org.school.backend.application.exception.GradeDataNotFoundException;
import org.school.backend.application.mappers.StudentGradeMapper;
import org.school.backend.application.usecases.GradeUseCase;
import org.school.backend.domain.gateaway.GradeLogGateAway;
import org.school.backend.domain.model.StudentGradeModel;

import java.util.Optional;

public class GradeUseCaseImpl implements GradeUseCase {

    private final GradeLogGateAway gradeLogGateAway;

    public GradeUseCaseImpl(final GradeLogGateAway repository){
        this.gradeLogGateAway = repository;

    }

    @Override
    public Optional<StudentGradeDto> findByStudentId(Integer id){
        Optional<StudentGradeModel> gradeModel = Optional.ofNullable(this.gradeLogGateAway.findByStudentId(id).orElseThrow(GradeDataNotFoundException::new));
        return Optional.of(StudentGradeMapper.toDto(gradeModel.get()));
    }
}
