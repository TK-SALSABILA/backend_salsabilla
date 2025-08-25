package org.school.backend.application.usecases.impl;

import org.school.backend.application.dto.request.SubjectParamDto;
import org.school.backend.application.dto.response.SubjectLogsDto;
import org.school.backend.application.exception.StudentDataNotFoundException;
import org.school.backend.application.exception.SubjectDataNotFoundException;
import org.school.backend.application.mappers.SubjectLogsMapper;
import org.school.backend.application.usecases.SubjectLogsUseCase;
import org.school.backend.domain.gateaway.LoggerGateway;
import org.school.backend.domain.gateaway.SubjectLogGateaway;
import org.school.backend.domain.model.SubjectModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class  SubjectLogsUseCaseImpl implements SubjectLogsUseCase {

    private final SubjectLogGateaway subjectLogGateaway;
    private final LoggerGateway logger;


    public SubjectLogsUseCaseImpl(final SubjectLogGateaway subjectLogGateaway, final LoggerGateway logger){
        this.logger = logger;
        this.subjectLogGateaway = subjectLogGateaway;
    }

    @Override
    public Optional<List<SubjectLogsDto>> findAll(SubjectParamDto params){
        logger.info("[subject use case] - Method Find All Started: {} ", params.toString());
      Optional<List<SubjectModel>> subjectLogsDtos;

      if (params.hasKeyword() || params.hasMandatory())
      {
          subjectLogsDtos = Optional.ofNullable(
                  this.subjectLogGateaway.findByFilter(params.q(),params.isMandatory())
          ).orElseThrow(SubjectDataNotFoundException::new);
      }else {
          subjectLogsDtos = Optional.ofNullable(
                  this.subjectLogGateaway.findAll(params.page(), params.rpp())
          ).orElseThrow(SubjectDataNotFoundException::new);
      }

      return Optional.of(SubjectLogsMapper.toListDto(subjectLogsDtos.get()));
    }

    @Override
    public Optional<SubjectLogsDto> findById(UUID id){
        logger.info("[subject use case] - Method Find By Id Started: {} ",  id.toString());
        SubjectModel subjectModel = this.subjectLogGateaway.findById(id).orElseThrow(() -> new SubjectDataNotFoundException("Subject Not found"));

        logger.info("[subject use case] - Find By Id response: {} ", subjectModel.toString());
        return Optional.of(SubjectLogsMapper.toDto(subjectModel));
    }

    @Override
    public boolean deleteById(UUID id){
        logger.info("[subject use case] - Method Delete By Id Started: {} ",  id.toString());

        try{
            Optional<SubjectModel> subjectModel = this.subjectLogGateaway.findById(id);

            if(subjectModel.isPresent()){
                this.subjectLogGateaway.deleteById(id);
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            throw new StudentDataNotFoundException(e);
        }
    }

    @Override
    public void update(UUID id, SubjectLogsDto record){
        logger.info("[subject use case] - Method Update Started: {} ",  record.toString());

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

        logger.info("[subject use case] - Updated Response: {} ",  updated.toString());

       this.subjectLogGateaway.update(id,updated);
    }

    @Override
    public void create(SubjectLogsDto payload){
        logger.info("[subject use case] - Method Create Started: {} ",  payload.toString());

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
