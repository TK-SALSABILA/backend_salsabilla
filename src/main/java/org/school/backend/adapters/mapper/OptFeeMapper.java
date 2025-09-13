package org.school.backend.adapters.mapper;


import org.school.backend.adapters.dto.OptFeeLogs;
import org.school.backend.adapters.dto.OptFeeReq;
import org.school.backend.domain.model.OperationalFeeModel;


import java.util.ArrayList;
import java.util.List;

public interface OptFeeMapper {

    public static OptFeeReq convertEntityToModel(
            final OperationalFeeModel entity
    ) {
        return OptFeeReq.builder()
                .studentId(entity.studentId())
                .paymentType(entity.paymentType())
                .transactionType(entity.transactionType())
                .amount(entity.amount())
                .description(entity.description())
                .status(entity.status())
                .transactionDate(entity.transactionDate())
                .build();
    }

    static List<OperationalFeeModel> convertModelsToEntity(final List<OptFeeLogs> recordEntities) {
        List<OperationalFeeModel> savingRecordEntities = new ArrayList<>();

        for (OptFeeLogs entity : recordEntities) {
            OperationalFeeModel model = new OperationalFeeModel(
                    entity.getId(),
                    entity.getStudentId(),
                    entity.getPaymentType(),
                    null,
                    entity.getDescription(),
                    entity.getStatus(),
                    entity.getTransactionDate(),
                    entity.getAmount()
            );
            savingRecordEntities.add(model);
        }

        return savingRecordEntities;
    }
}
