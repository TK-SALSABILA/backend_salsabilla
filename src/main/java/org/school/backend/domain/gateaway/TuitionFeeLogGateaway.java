package org.school.backend.domain.gateaway;

import org.school.backend.domain.model.TuitionFeeModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TuitionFeeLogGateaway {
    Optional<List<TuitionFeeModel>> findByStudentIdsAndMonthAndStatus(List<UUID> studentIds, String month);
    void createTuition(TuitionFeeModel record);
    void saveAll(List<TuitionFeeModel> fee);
}