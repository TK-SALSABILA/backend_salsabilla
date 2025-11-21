package org.school.backend.application.usecases;

import org.school.backend.application.dto.request.ActivityRequestDto;
import org.school.backend.application.dto.response.ActivityResponseDto;

import java.util.List;

public interface ActivityUseCase {
    List<ActivityResponseDto> getActivity(int page, int rpp);
    void createActivity(ActivityRequestDto record);

}
