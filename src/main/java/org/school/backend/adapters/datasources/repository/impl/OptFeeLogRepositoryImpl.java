package org.school.backend.adapters.datasources.repository.impl;

import org.school.backend.adapters.configuration.ApplicationConfigProperties;
import org.school.backend.adapters.datasources.repository.JpaOptFeeRepository;
import org.school.backend.adapters.datasources.repository.OptFeeLogRepository;
import org.school.backend.adapters.datasources.repository.StudentLogsRepository;
import org.school.backend.adapters.datasources.specification.OptFeeSpecification;
import org.school.backend.adapters.dto.OptFeeLogs;
import org.school.backend.adapters.dto.OptFeeReq;
import org.school.backend.adapters.dto.StudentLogs;
import org.school.backend.adapters.schema.jpa.OptFeeJpa;
import org.school.backend.adapters.schema.jpa.SavingLogJpa;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class OptFeeLogRepositoryImpl implements OptFeeLogRepository {

    private final ApplicationConfigProperties applicationConfigProperties;
    private final JpaOptFeeRepository jpaOptFeeRepository;
    private final StudentLogsRepository studentLogsRepository;

    public OptFeeLogRepositoryImpl(ApplicationConfigProperties applicationConfigProperties, final JpaOptFeeRepository jpaOptFeeRepository,final StudentLogsRepository studentLogsRepository){
        this.applicationConfigProperties = applicationConfigProperties;
        this.jpaOptFeeRepository = jpaOptFeeRepository;
        this.studentLogsRepository = studentLogsRepository;
    }

    @Override
    public List<OptFeeLogs> findFee(int page, int rpp, String studentName, String status, String month, UUID classId) {
        List<OptFeeLogs> feeData = new ArrayList<>();
        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()) {
            case "postgresql" -> {
                Specification<OptFeeJpa> spec = OptFeeSpecification.hasStudentName(studentName)
                        .and(OptFeeSpecification.hasStatus(status))
                        .and(OptFeeSpecification.hasMonth(month))
                        .and(OptFeeSpecification.hasClassId(classId));

                jpaOptFeeRepository.findAll(spec)
                        .forEach(entity -> feeData.add(new OptFeeLogs(
                                entity.getId(),
                                entity.getStudentId(),
                                entity.getPaymentType(),
                                entity.getAmount(),
                                entity.getTransactionDate(),
                                entity.getDescription()
                        )));
            }
            default -> throw new IllegalArgumentException("Unsupported database: " + applicationConfigProperties.getDatabaseDefault());

        }

        return feeData;
    }

    @Override
    public void create(OptFeeReq record) {
        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()){
            case"postgresql" -> {
                OptFeeJpa feeData = new OptFeeJpa(
                        record.paymentType,
                        record.transactionType,
                        record.transactionDate,
                        record.amount,
                        record.description,
                        record.studentId
                );
                jpaOptFeeRepository.save(feeData);
            }
            default -> throw new IllegalArgumentException("Unsupported database: " + applicationConfigProperties.getDatabaseDefault());
        }
    }
}
