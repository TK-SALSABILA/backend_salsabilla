package org.school.backend.application.usecases;

import org.school.backend.application.dto.request.TuitonFeeReqDto;
import org.school.backend.application.dto.response.TuitonFeeResDto;

import java.util.List;
import java.util.Optional;

public interface TuitionFeeUseCase {
    Optional<List<TuitonFeeResDto> > findAllTuition(Object classId, String month);

    void createTuition(TuitonFeeReqDto record);
}
