package org.school.backend.application.mappers;

import org.school.backend.adapters.dto.ActivityStudentParticipant;
import org.school.backend.application.dto.request.ActivityStudentParticipantRequestDto;
import org.school.backend.application.dto.response.ActivityStudentParticipantResponseDto;
import org.school.backend.domain.model.ActivityStudentParticipantModel;

import java.util.ArrayList;
import java.util.List;

import static org.school.backend.application.utils.DateTimeFormatterConfig.parseDate;
import static org.school.backend.application.utils.DateTimeFormatterConfig.toStringFormat;

public class ActivityStudentParticipantMapper {

    public static List<ActivityStudentParticipantResponseDto> toDto(List<ActivityStudentParticipantModel> entities)
    {
        List<ActivityStudentParticipantResponseDto> resultData = new ArrayList<>();
        entities.forEach((entity) -> {
            Integer remainingAmount = entity.amountRequired() - entity.amountPaid();

            resultData.add(new ActivityStudentParticipantResponseDto(
                    entity.id(),
                    entity.activityId(),
                    entity.studentId(),
                    entity.gradeId(),
                    entity.studentName(),
                    entity.studentNis(),
                    entity.gradeName(),
                    entity.amountRequired(),
                    entity.amountPaid(),
                    remainingAmount,
                    entity.paymentStatus(),
                    toStringFormat(entity.lastPaymentDate())
            ));
        });
        return resultData;
    }

    public static List<ActivityStudentParticipantModel> convertModelsToEntities(List<ActivityStudentParticipantRequestDto> record){
        List<ActivityStudentParticipantModel> resultData = new ArrayList<>();
        record.forEach((entity) -> resultData.add(
                new ActivityStudentParticipantModel(
                        entity.activityId(),
                        entity.studentId(),
                        entity.gradeId(),
                        entity.studentName(),
                        entity.studentNis(),
                        entity.gradeName(),
                        entity.amountRequired(),
                        entity.amountPaid(),
                        entity.remainingAmount(),
                        entity.paymentStatus()
                )));
        return resultData;
    }


}
