package org.school.backend.application.usecases;

import org.school.backend.application.dto.request.OperationFeeReqDto;
import org.school.backend.application.dto.response.OperationFeeResDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OperationalFeeUseCase {
    Optional<List<OperationFeeResDto>> findAllFee(        int page,
                                                          int rpp,
                                                          String q,
                                                          String status,
                                                          String month,
                                                          UUID classId);
    void createPayment(OperationFeeReqDto record);
}
