package org.school.backend.adapters.mapper;

import org.school.backend.adapters.dto.SavingLogReq;
import org.school.backend.adapters.dto.SavingLogs;
import org.school.backend.adapters.dto.StudentDetails;
import org.school.backend.domain.model.SavingModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface SavingLogsMapper {

    public static SavingLogReq convertEntityToModel(
            final SavingModel entity
    ) {
        return SavingLogReq.builder()
                .studentId(entity.studentId())
                .paymentType(entity.paymentType())
                .amount(entity.amount())
                .transactionDate(entity.transactionDate())
                .description(entity.description())
                .build();
    }


    static List<SavingModel> convertModelsToEntity(final List<SavingLogs> recordEntities) {
        List<SavingModel> savingRecordEntities = new ArrayList<>();

        for (SavingLogs entity : recordEntities) {
            SavingModel model = new SavingModel(
                    entity.getId(),
                    entity.getStudentId(),
                    entity.getPaymentType(),
                    null,
                    entity.getTransactionDate(),
                    entity.getTotalAmount(),
                    entity.getDescription()
            );
            savingRecordEntities.add(model);
        }

        return savingRecordEntities;
    }

    static Optional<SavingModel> convertModelToEntity(Optional<SavingLogs> entity) {
        if (entity.isPresent()) {
            SavingLogs log = entity.get();

            return Optional.of(new SavingModel(
                    log.getId(),
                    log.getStudentId(),
                    log.getPaymentType(),
                    null,
                    log.getTransactionDate(),
                    log.getTotalAmount(),
                    log.getDescription()
            ));
        } else {
            return Optional.empty();
        }
    }

}
