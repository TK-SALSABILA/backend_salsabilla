package org.school.backend.application.usecases.impl;

import org.school.backend.application.dto.ParentDto;
import org.school.backend.application.exception.ParentDataNotFoundException;
import org.school.backend.application.mappers.ParentMapper;
import org.school.backend.application.usecases.ParentUseCase;
import org.school.backend.domain.gateaway.ParentLogGateaway;
import org.school.backend.domain.model.ParentModel;

import java.util.Optional;
import java.util.UUID;

import static org.school.backend.application.utils.DateTimeFormatterConfig.parseDate;

public class ParentUseCaseImpl implements ParentUseCase {

    private final ParentLogGateaway parentLogGateaway;

    public ParentUseCaseImpl(final ParentLogGateaway parentLogGateaway){
        this.parentLogGateaway = parentLogGateaway;
    }

    @Override
    public Optional<ParentDto> findByStudentId(UUID id){
        ParentModel parentModel = parentLogGateaway.findByStudentId(id)
                .orElseThrow(() -> new ParentDataNotFoundException("student with id not found"));
        return Optional.of(ParentMapper.toDto(parentModel));
    }

    @Override
    public void updateByStudentId(Object id, ParentDto record) {
        ParentModel existing = parentLogGateaway.findByStudentId(id)
                .orElseThrow(() -> new ParentDataNotFoundException("student with id not found"));


        ParentModel updated = new ParentModel(
                record.fatherName() != null ? record.fatherName() : existing.fatherName(),
                record.fatherDateBirth() != null ? parseDate(record.fatherDateBirth()) : existing.fatherDateBirth(),
                record.fatherNik() != null ? record.fatherNik() : existing.fatherNik(),
                record.fatherEducation() != null ? record.fatherEducation() : existing.fatherEducation(),
                record.fatherJob() != null ? record.fatherJob() : existing.fatherJob(),
                record.fatherCitizen() != null ? record.fatherCitizen() : existing.fatherCitizen(),
                record.fatherIncome() != null ? record.fatherIncome() : existing.fatherIncome(),
                record.fatherAddress() != null ? record.fatherAddress() : existing.fatherAddress(),
                record.fatherPhone() != null ? record.fatherPhone() : existing.fatherPhone(),

                record.motherName() != null ? record.motherName() : existing.motherName(),
                record.motherDateBirth() != null ? parseDate(record.motherDateBirth()) : existing.motherDateBirth(),
                record.motherNik() != null ? record.motherNik() : existing.motherNik(),
                record.motherEducation() != null ? record.motherEducation() : existing.motherEducation(),

                record.motherCitizen() != null ? record.motherCitizen() : existing.motherCitizen(),
                record.motherIncome() != null ? record.motherIncome() : existing.motherIncome(),
                record.motherAddress() != null ? record.motherAddress() : existing.motherAddress(),
                record.motherPhone() != null ? record.motherPhone() : existing.motherPhone()
        );

        parentLogGateaway.update(id,updated);
    }
}