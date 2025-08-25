package org.school.backend.application.usecases.impl;

import org.school.backend.application.dto.StudentDetailsDto;
import org.school.backend.application.dto.request.StudentParamDto;
import org.school.backend.application.dto.request.StudentRequestDto;
import org.school.backend.application.dto.response.StudentsLogsOutputDto;
import org.school.backend.application.exception.StudentDataNotFoundException;
import org.school.backend.application.mappers.StudentDetailMapper;
import org.school.backend.application.mappers.StudentLogsMapper;
import org.school.backend.application.mappers.StudentRequestMapper;
import org.school.backend.application.usecases.StudentLogsUseCase;
import org.school.backend.domain.gateaway.LoggerGateway;
import org.school.backend.domain.gateaway.ParentLogGateaway;
import org.school.backend.domain.gateaway.StudentLogGateaway;
import org.school.backend.domain.model.StudentModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.school.backend.application.utils.DateTimeFormatterConfig.parseDate;

public class StudentLogsUseCaseImpl implements StudentLogsUseCase {

    private final StudentLogGateaway studentLogGateaway;
    private final LoggerGateway logger;

    public StudentLogsUseCaseImpl(final StudentLogGateaway studentLogGateaway, final LoggerGateway logger){
        this.logger = logger;
        this.studentLogGateaway = studentLogGateaway;
    }

    @Override
    public Optional<List<StudentsLogsOutputDto>> findAll(StudentParamDto params) {
        logger.info("[student use case] - Method Find All Started: {} ", params.toString());
        Optional<List<StudentModel>> studentModels;

        if (params.hasAnyFilter()) {
            studentModels = Optional.ofNullable(
                    this.studentLogGateaway.findByFilter(params.q(), params.classId())
            ).orElseThrow(StudentDataNotFoundException::new);
        } else {
            studentModels = Optional.ofNullable(
                    this.studentLogGateaway.findAll(params.rpp(), params.page())
            ).orElseThrow(StudentDataNotFoundException::new);
        }

        return Optional.of(StudentLogsMapper.toListDto(studentModels.get()));
    }

    @Override
    public Optional<StudentDetailsDto> findById(UUID id){
        logger.info("[student use case] - Method Find By Id Started: {} ", id.toString());

        StudentModel studentModel = studentLogGateaway.findById(id)
                .orElseThrow(() -> new StudentDataNotFoundException("Student with id not found"));

        logger.info("[student use case] - Find By Id response: {} ", studentModel.toString());

        return Optional.of(StudentDetailMapper.toDto(studentModel));
    }

    @Override
    public void create(StudentRequestDto record){
        logger.info("[student use case] - Method Create  Started: {} ", record.toString());

        StudentModel studentRecord = StudentRequestMapper.toModel(record);
        this.studentLogGateaway.create(studentRecord);
    }

    @Override
    public void updateStudent(Object id,StudentDetailsDto record) {
        logger.info("[student use case] - Method Update  Started: {} ", record.toString());

            StudentModel existing = studentLogGateaway.findById(id).orElseThrow(() -> new StudentDataNotFoundException("student not found"));

            StudentModel updated = new StudentModel(
                    (UUID) id,
                    record.fullName() != null ? record.fullName() : existing.fullName(),
                    record.nickName() != null ? record.nickName() : existing.nickName(),
                    record.nik() != null ? record.nik() : existing.nik(),
                    record.gender() != null ? record.gender() : existing.gender(),
                    record.dateBirth() != null ? parseDate(record.dateBirth()) : existing.dateBirth(),
                    record.birthOrder() != null ? record.birthOrder() : existing.birthOrder(),
                    record.tribe() != null ? record.tribe() : existing.tribe(),
                    record.address() != null ? record.address() : existing.address(),
                    record.height() != null ? record.height() : existing.height(),
                    record.weight() != null ? record.weight() : existing.weight(),
                    null,
                    null
            );

            logger.info("[student use case] - Updated response: {} ", updated.toString());


            studentLogGateaway.update(id,updated);

    }
}
