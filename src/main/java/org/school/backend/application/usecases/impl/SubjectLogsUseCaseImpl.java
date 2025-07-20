package org.school.backend.application.usecases.impl;

import org.school.backend.adapters.schema.jpa.SubjectLogJpa;
import org.school.backend.application.dto.response.SubjectLogsDto;
import org.school.backend.application.exception.SubjectDataNotFoundException;
import org.school.backend.application.mappers.SubjectLogsMapper;
import org.school.backend.application.usecases.SubjectLogsUseCase;
import org.school.backend.domain.gateaway.SubjectLogGateaway;
import org.school.backend.domain.model.SubjectModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class SubjectLogsUseCaseImpl implements SubjectLogsUseCase {

    private final SubjectLogGateaway subjectLogGateaway;

    public SubjectLogsUseCaseImpl(final SubjectLogGateaway subjectLogGateaway){
        this.subjectLogGateaway = subjectLogGateaway;
    }

    @Override
    public Optional<List<SubjectLogsDto>> findAll(int page,int rpp){
        Optional<List<SubjectModel>> subjectModels = Optional.ofNullable(this.subjectLogGateaway.findAll(page, rpp)).orElseThrow(SubjectDataNotFoundException::new);
        return Optional.of(SubjectLogsMapper.toListDto(subjectModels.get()));
    }

    @Override
    public Optional<SubjectLogsDto> findById(UUID id){
        Optional<SubjectModel> subjectModel = Optional.ofNullable(this.subjectLogGateaway.findById(id)).orElseThrow(SubjectDataNotFoundException::new);
        return Optional.of(SubjectLogsMapper.toDto(subjectModel.get()));
    }

    @Override
    public boolean deleteById(UUID id){
        try{
            Optional<SubjectModel> subjectModel = this.subjectLogGateaway.findById(id);

            if(subjectModel.isPresent()){
                this.subjectLogGateaway.deleteById(id);
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(UUID id, SubjectLogsDto record){
        SubjectModel existing = this.subjectLogGateaway.findById(id)
                .orElseThrow(() -> new SubjectDataNotFoundException("Subject not found with ID: " + id));

        SubjectModel updated = new SubjectModel(
                id,
                record.subjectName() != null ? record.subjectName() : existing.subjectName(),
                record.subjectCode() != null ? record.subjectCode() : existing.subjectCode(),
                record.gradeLevel() != null ? record.gradeLevel() : existing.gradeLevel(),
                record.isMandatory() != null ? record.isMandatory() : existing.isMandatory(),
                record.description() != null ? record.description() : existing.description()
        );

       this.subjectLogGateaway.update(id,updated);
    }

    @Override
    public void create(SubjectLogsDto payload){
        final SubjectModel record = new SubjectModel(
                payload.subjectName(),
                payload.subjectCode(),
                payload.gradeLevel(),
                payload.isMandatory(),
                payload.description()
        );
        this.subjectLogGateaway.create(record);
    }
}
