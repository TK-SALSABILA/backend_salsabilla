package org.school.backend.application.usecases;

import org.school.backend.application.dto.request.ActivityRequestDto;
import org.school.backend.application.dto.response.ActivityResponseDto;

import java.util.List;
import java.util.UUID;

public interface ActivityUseCase {
    List<ActivityResponseDto> getActivity(int page, int rpp);
    ActivityResponseDto getActivityDetail(UUID id);
    void createActivity(ActivityRequestDto record);
}
