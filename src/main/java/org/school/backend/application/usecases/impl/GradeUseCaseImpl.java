package org.school.backend.application.usecases.impl;

import org.school.backend.application.dto.GradeDto;
import org.school.backend.application.exception.GradeDataNotFoundException;
import org.school.backend.application.mappers.GradeMapper;
import org.school.backend.application.usecases.GradeUseCase;
import org.school.backend.domain.gateaway.GradeLogGateaway;
import org.school.backend.domain.model.GradeModel;

import java.util.List;
import java.util.Optional;

public class GradeUseCaseImpl implements GradeUseCase {

    private final GradeLogGateaway gradeLogGateaway;

    public GradeUseCaseImpl(
            final GradeLogGateaway gradeLogGateaway
    ){
        this.gradeLogGateaway =gradeLogGateaway;
    }

    @Override
    public Optional<List<GradeDto>> findAll(int page, int rpp){
        Optional<List<GradeModel>> gradeModels = Optional.ofNullable(this.gradeLogGateaway.findAll(page, rpp)).orElseThrow(GradeDataNotFoundException::new);
        return Optional.of(GradeMapper.toListDto(gradeModels.get()));
    }

    @Override
    public void create(GradeDto reocrd){
        this.gradeLogGateaway.create(GradeMapper.toGradeModel(reocrd));
    }
}
