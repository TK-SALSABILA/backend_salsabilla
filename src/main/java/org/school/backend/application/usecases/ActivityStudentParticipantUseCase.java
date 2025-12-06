package org.school.backend.application.usecases;

import org.school.backend.application.dto.request.ActivityPaymentRequest;
import org.school.backend.application.dto.request.ActivityStudentParticipantRequestDto;
import org.school.backend.application.dto.response.ActivityStudentParticipantResponseDto;

import java.util.List;
import java.util.UUID;

public interface ActivityStudentParticipantUseCase {
    List<ActivityStudentParticipantResponseDto> getListStudent(UUID activityId, int page, int rpp);
    void createActivityStudent(List<ActivityStudentParticipantRequestDto> record);
    void createPaymentStudent(ActivityPaymentRequest request);

}
