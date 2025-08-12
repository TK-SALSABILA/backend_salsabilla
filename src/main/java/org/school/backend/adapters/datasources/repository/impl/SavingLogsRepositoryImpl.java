package org.school.backend.adapters.datasources.repository.impl;

import org.school.backend.adapters.configuration.ApplicationConfigProperties;
import org.school.backend.adapters.datasources.repository.JpaSavingLogsRepository;
import org.school.backend.adapters.datasources.repository.SavingLogsRepository;
import org.school.backend.adapters.datasources.repository.StudentLogsRepository;
import org.school.backend.adapters.dto.SavingLogReq;
import org.school.backend.adapters.dto.SavingLogs;
import org.school.backend.adapters.dto.StudentDetails;
import org.school.backend.adapters.dto.StudentLogs;
import org.school.backend.adapters.schema.jpa.SavingLogJpa;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SavingLogsRepositoryImpl implements SavingLogsRepository {

    final ApplicationConfigProperties applicationConfigProperties;
    final JpaSavingLogsRepository jpaSavingLogsRepository;
    final StudentLogsRepository studentLogsRepository;

    public SavingLogsRepositoryImpl(
            ApplicationConfigProperties applicationConfigProperties,
            final JpaSavingLogsRepository jpaSavingLogsRepository,
            final StudentLogsRepository studentLogsRepository
    ){
        this.applicationConfigProperties = applicationConfigProperties;
        this.jpaSavingLogsRepository = jpaSavingLogsRepository;
        this.studentLogsRepository = studentLogsRepository;
    }


    @Override
    public List<SavingLogs> findAll(int page, int rpp) {
        List<SavingLogs> result = new ArrayList<>();

        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()) {
            case "postgresql" -> {
                // get all dulu semua log saving
                List<SavingLogJpa> logs = jpaSavingLogsRepository.findAll();

                // get list all studentID unik
                Set<UUID> studentIds = logs.stream()
                        .map(SavingLogJpa::getStudentId)
                        .collect(Collectors.toSet());

                //Batch fetch student name
                Map<UUID, String> studentNameMap = studentLogsRepository.findAllStudentId(studentIds).stream()
                        .collect(Collectors.toMap(
                                StudentLogs::getId,
                                StudentLogs::getFullName
                        ));

                Map<UUID, Integer> balanceMap = jpaSavingLogsRepository.sumAmountByStudentIdIn(studentIds).stream()
                        .collect(Collectors.toMap(
                                entry -> (UUID) entry[0],
                                entry -> (Integer) entry[1]
                        ));

                for (SavingLogJpa entity : logs) {
                    String studentName = studentNameMap.getOrDefault(entity.getStudentId(),"Siswa Tidak Dikenal");
                    Integer totalAmount = balanceMap.getOrDefault(entity.getStudentId(),0);

                    SavingLogs savingLog = new SavingLogs(
                            entity.getId(),
                            entity.getStudentId(),
                            studentName,
                            entity.getPaymentType(),
                            totalAmount,
                            entity.getTransactionDate(),
                            entity.getDescription()
                    );

                    result.add(savingLog);
                }
            }
        }

        return result;
    }


    @Override
    public void create(SavingLogReq record) {
        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()){
            case"postgresql" -> {
                SavingLogJpa savingData = new SavingLogJpa(
                        record.getPaymentType(),
                        record.getTransactionType(),
                        record.getTransactionDate(),
                        record.getAmount(),
                        record.getDescription(),
                        record.getStudentId()
                );
                jpaSavingLogsRepository.save(savingData);
            }
            default -> throw new IllegalArgumentException("Unsupported database: " + applicationConfigProperties.getDatabaseDefault());
        }
    }

    @Override
    public Integer getBalance(UUID studentId) {
        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()) {
            case "postgresql" -> {
                return jpaSavingLogsRepository.sumAmountByStudentId(studentId);
            }
            default -> throw new IllegalArgumentException(
                    "Unsupported database: " + applicationConfigProperties.getDatabaseDefault()
            );
        }
    }


    @Override
    public void withDrawSaving(UUID studentId, Integer amount, String description) {
        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()) {
            case "postgresql" -> {
                SavingLogJpa withdraw = new SavingLogJpa(
                        "TABUNGAN",
                        "WITHDRAW",
                        LocalDateTime.now(),
                        -amount,
                        description,
                        studentId
                );

                jpaSavingLogsRepository.save(withdraw);
            }
            default -> throw new IllegalArgumentException(
                    "Unsupported database: " + applicationConfigProperties.getDatabaseDefault()
            );
        }
    }
}
