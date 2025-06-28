package org.school.backend.application.usecases.impl;

import org.school.backend.application.dto.GradeDto;
import org.school.backend.application.exception.GradeDataNotFoundException;
import org.school.backend.application.mappers.GradeMapper;
import org.school.backend.application.usecases.GradeUseCase;
import org.school.backend.domain.gateaway.GradeLogGateAway;
import org.school.backend.domain.model.GradeModel;

import java.util.Optional;

public class GradeUseCaseImpl implements GradeUseCase {

    private final GradeLogGateAway gradeLogGateAway;

    public GradeUseCaseImpl(final GradeLogGateAway repository){
        this.gradeLogGateAway = repository;

    }

    @Override
    public Optional<GradeDto> findByStudentId(Integer id){
        Optional<GradeModel> gradeModel = Optional.ofNullable(this.gradeLogGateAway.findByStudentId(id).orElseThrow(GradeDataNotFoundException::new));
        return Optional.of(GradeMapper.toDto(gradeModel.get()));
    }
}
