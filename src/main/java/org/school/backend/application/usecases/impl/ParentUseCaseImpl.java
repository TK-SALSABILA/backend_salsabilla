package org.school.backend.application.usecases.impl;

import org.school.backend.application.dto.ParentDto;
import org.school.backend.application.exception.ParentDataNotFoundException;
import org.school.backend.application.mappers.ParentMapper;
import org.school.backend.application.usecases.ParentUseCase;
import org.school.backend.domain.gateaway.ParentLogGateaway;
import org.school.backend.domain.model.ParentModel;

import java.util.Optional;
import java.util.UUID;

public class ParentUseCaseImpl implements ParentUseCase {

    private final ParentLogGateaway parentLogGateaway;

    public ParentUseCaseImpl(final ParentLogGateaway parentLogGateaway){
        this.parentLogGateaway = parentLogGateaway;
    }

    @Override
    public Optional<ParentDto> findByStudentId(UUID id){
        Optional<ParentModel> parentModel = Optional.ofNullable(this.parentLogGateaway.findByStudentId(id).orElseThrow(ParentDataNotFoundException::new));
        return Optional.of(ParentMapper.toDto(parentModel.get()));
    }
}