package org.school.backend.application.usecases.impl;

import org.school.backend.application.dto.GradeDto;
import org.school.backend.application.exception.GradeDataNotFoundException;
import org.school.backend.application.mappers.GradeMapper;
import org.school.backend.application.usecases.GradeUseCase;
import org.school.backend.domain.gateaway.GradeLogGateaway;
import org.school.backend.domain.gateaway.LoggerGateway;
import org.school.backend.domain.model.GradeModel;

import java.util.List;
import java.util.Optional;

public class GradeUseCaseImpl implements GradeUseCase {

    private final GradeLogGateaway gradeLogGateaway;
    private final LoggerGateway logger;

    public GradeUseCaseImpl(
            final GradeLogGateaway gradeLogGateaway,
            final LoggerGateway logger
    ){
        this.gradeLogGateaway =gradeLogGateaway;
        this.logger = logger;
    }

    @Override
    public Optional<List<GradeDto>> findAll(int page, int rpp){
        logger.info("[grade use case] - Method find all Started: {} ");

        Optional<List<GradeModel>> gradeModels = Optional.ofNullable(this.gradeLogGateaway.findAll(page, rpp)).orElseThrow(GradeDataNotFoundException::new);
        logger.info("[grade use case] - Find All Response: {} ", gradeModels.toString());

        return Optional.of(GradeMapper.toListDto(gradeModels.get()));
    }

    @Override
    public void create(GradeDto reocrd){
        logger.info("[grade use case] - Method create Started: {} ",reocrd.toString());

        this.gradeLogGateaway.create(GradeMapper.toGradeModel(reocrd));
    }
}
