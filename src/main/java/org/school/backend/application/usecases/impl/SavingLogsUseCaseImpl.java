package org.school.backend.application.usecases.impl;

import org.school.backend.application.dto.request.SavingRequestDto;
import org.school.backend.application.dto.response.SavingLogOutputDto;
import org.school.backend.application.exception.SavingDataNotFoundException;
import org.school.backend.application.exception.StudentDataNotFoundException;
import org.school.backend.application.mappers.SavingMapper;
import org.school.backend.application.usecases.SavingLogsUseCase;
import org.school.backend.domain.gateaway.SavingLogGateaway;
import org.school.backend.domain.gateaway.StudentLogGateaway;
import org.school.backend.domain.model.SavingModel;
import org.school.backend.domain.model.StudentModel;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.school.backend.application.utils.DateTimeFormatterConfig.parseDate;

public class SavingLogsUseCaseImpl implements SavingLogsUseCase {

    private final SavingLogGateaway savingLogGateaway;
    private final StudentLogGateaway studentLogGateaway;

    public SavingLogsUseCaseImpl(final SavingLogGateaway savingLogGateaway,final StudentLogGateaway studentLogGateaway){
        this.savingLogGateaway = savingLogGateaway;
        this.studentLogGateaway = studentLogGateaway;
    }

    @Override
    public Optional<List<SavingLogOutputDto>> findAll(int page, int rpp) {
        List<SavingModel> savings = savingLogGateaway.findAll(page, rpp)
                .orElseThrow(SavingDataNotFoundException::new);

        List<SavingLogOutputDto> result = mapLatestSavingToDto(savings);

        return Optional.of(result);
    }


    @Override
    public void create(final SavingRequestDto record) {
       final SavingModel savingModel =  new SavingModel(
               record.studentId(),
               record.paymentType(),
               record.transactionType(),
               parseDate(record.transactionDate()),
               record.amount(),
               record.description()
        );
       this.savingLogGateaway.create(savingModel);
    }

    private List<SavingLogOutputDto> mapLatestSavingToDto(List<SavingModel> savings) {
        return getLatestSavingsByStudent(savings).values().stream()
                .map(this::mapToDto)
                .toList();
    }

    private Map<UUID, SavingModel> getLatestSavingsByStudent(List<SavingModel> savings) {
        return savings.stream()
                .collect(Collectors.toMap(
                        SavingModel::studentId,
                        Function.identity(),
                        this::getLatestSaving
                ));
    }

    private SavingModel getLatestSaving(SavingModel s1, SavingModel s2) {
        return   s2.transactionDate().isAfter(s1.transactionDate()) ? s2 : s1;
    }

    private SavingLogOutputDto mapToDto(SavingModel saving) {
        StudentModel student = studentLogGateaway.findById(saving.studentId())
                .orElseThrow(() -> new SavingDataNotFoundException("Student not found for ID: " + saving.studentId()));
        return SavingMapper.toDto(saving, student);
    }

}
