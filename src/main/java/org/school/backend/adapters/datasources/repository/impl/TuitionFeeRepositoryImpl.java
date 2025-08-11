package org.school.backend.adapters.datasources.repository.impl;

import org.school.backend.adapters.configuration.ApplicationConfigProperties;
import org.school.backend.adapters.datasources.repository.*;
import org.school.backend.adapters.dto.SavingLogs;
import org.school.backend.adapters.dto.StudentDetails;
import org.school.backend.adapters.dto.TuitionFeeLogReq;
import org.school.backend.adapters.dto.TuitionFeeLogs;
import org.school.backend.adapters.schema.jpa.SavingLogJpa;
import org.school.backend.adapters.schema.jpa.StudentGradeJpa;
import org.school.backend.adapters.schema.jpa.TuitionFeeJpa;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TuitionFeeRepositoryImpl implements TuitionFeeRepository {

    final ApplicationConfigProperties applicationConfigProperties;
    final JpaTuitionFeeRepository jpaTuitionFeeRepository;


    public TuitionFeeRepositoryImpl(
            ApplicationConfigProperties applicationConfigProperties,
            final JpaTuitionFeeRepository jpaTuitionFeeRepository
    ){
        this.applicationConfigProperties = applicationConfigProperties;
        this.jpaTuitionFeeRepository = jpaTuitionFeeRepository;

    }

    @Override
    public List<TuitionFeeLogs> findByStudentIdsAndMonthAndStatus(List<UUID> studentIds, String month) {
        List<TuitionFeeLogs> result = new ArrayList<>();
        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()){
            case "postgresql" -> jpaTuitionFeeRepository.findByStudentIdsAndMonthAndStatus(studentIds, month)
                    .forEach(entity -> result.add(new TuitionFeeLogs(
                            entity.getId(),
                            entity.getStudentId(),
                            entity.getMonth(),
                            entity.getAmount(),
                            entity.getStatus(),
                            entity.getTransactionDate()
                    )));

            default -> throw new IllegalArgumentException(applicationConfigProperties.getDatabaseDefault().toLowerCase());
        }
        return (result);
    }

    @Override
    public void createTuition(TuitionFeeLogReq record) {
        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()) {
            case "postgresql" -> {
                Optional<TuitionFeeJpa> existing = jpaTuitionFeeRepository
                        .findByStudentIdsAndMonth(record.getStudentId(), record.getMonth());

                if (existing.isPresent()) {
                    // Update existing data
                    TuitionFeeJpa tuitionData = existing.get();
                    tuitionData.setPaymentType(record.getPaymentType());
                    tuitionData.setTransactionType(record.getTransactionType());
                    tuitionData.setTransactionDate(record.getTransactionDate());
                    tuitionData.setAmount(record.getAmount());
                    tuitionData.setStatus(record.getStatus());

                    jpaTuitionFeeRepository.save(tuitionData);
                } else {
                    // Insert baru kalau belum ada
                    TuitionFeeJpa tuitionData = new TuitionFeeJpa(
                            record.getPaymentType(),
                            record.getTransactionType(),
                            record.getTransactionDate(),
                            record.getMonth(),
                            record.getAmount(),
                            record.getStatus(),
                            null,
                            record.getStudentId()
                    );
                    jpaTuitionFeeRepository.save(tuitionData);
                }
            }
            default -> throw new IllegalArgumentException("Unsupported database: " + applicationConfigProperties.getDatabaseDefault());
        }
    }

    @Override
    public void saveAllTuition(List<TuitionFeeLogs> record) {
        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()){
            case "postgresql" -> {
                 List<TuitionFeeJpa> entites = record.stream()
                        .map(req -> new TuitionFeeJpa(
                                null,
                                null,
                                req.getTransactionDate(),
                                req.getMonth(),
                                req.getAmount(),
                                req.getStatus(),
                                null,
                                req.getStudentId()
                        ))
                        .collect(Collectors.toList());

                jpaTuitionFeeRepository.saveAll(entites);
            }
            default -> throw new IllegalArgumentException("Unsupported database: " + applicationConfigProperties.getDatabaseDefault());

        }
    }
}
