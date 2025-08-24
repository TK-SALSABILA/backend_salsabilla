package org.school.backend.adapters.mapper;

import org.school.backend.adapters.dto.TuitionFeeLogReq;
import org.school.backend.adapters.dto.TuitionFeeLogs;
import org.school.backend.domain.model.TuitionFeeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TuitionFeeMapper {

    public static TuitionFeeLogReq convertEntityToModel(
            final TuitionFeeModel entity
    ) {
        return TuitionFeeLogReq.builder()
                .studentId(entity.studentId())
                .paymentType(entity.paymentType())
                .month(entity.month())
                .transactionType(entity.transactionType())
                .status(entity.status())
                .amount(entity.amount())
                .transactionDate(entity.transactionDate())
                .build();
    }
    public static List<TuitionFeeLogs> convertModelsToEntities(
            final List<TuitionFeeModel> models
    ) {
        return models.stream()
                .map(model -> TuitionFeeLogs.builder()
                        .id(model.id())
                        .studentId(model.studentId())
                        .month(model.month())
                        .amount(model.amount())
                        .status(model.status())
                        .transactionDate(model.transactionDate())
                        .build()
                )
                .toList();
    }


    static List<TuitionFeeModel> convertModelsToEntity(final List<TuitionFeeLogs> recordEntities) {
        List<TuitionFeeModel> tuitionFeeModel = new ArrayList<>();

        for (TuitionFeeLogs entity : recordEntities) {
            TuitionFeeModel model = new TuitionFeeModel(
                    entity.getId(),
                    entity.getStudentId(),
                    entity.getPaymentType(),
                    null,
                    entity.getStatus(),
                    entity.getTransactionDate(),
                    entity.getMonth(),
                    entity.getAmount()
            );
            tuitionFeeModel.add(model);
        }

        return tuitionFeeModel;
    }

    static Optional<TuitionFeeModel> convertModelToEntity(Optional<TuitionFeeLogs> entity) {
        if (entity.isPresent()) {
            TuitionFeeLogs log = entity.get();

            return Optional.of(new TuitionFeeModel(
                    log.getId(),
                    log.getStudentId(),
                    null,
                    null,
                    log.getStatus(),
                    log.getTransactionDate(),
                    log.getMonth(),
                    log.getAmount()
            ));
        } else {
            return Optional.empty();
        }
    }
}
