package org.school.backend.domain.gateaway;

import org.school.backend.domain.model.OperationalFeeModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OperationalFeeGateway {
    Optional<List<OperationalFeeModel>> findOperationalfee (int page, int rpp, String studentName, String status, String month, UUID classId);
    void createFee(OperationalFeeModel record);
}
