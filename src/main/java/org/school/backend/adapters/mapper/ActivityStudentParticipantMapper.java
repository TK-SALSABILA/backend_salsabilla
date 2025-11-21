package org.school.backend.adapters.mapper;

import org.school.backend.adapters.dto.ActivityStudentParticipant;
import org.school.backend.domain.model.ActivityStudentParticipantModel;

import java.util.ArrayList;
import java.util.List;

public interface ActivityStudentParticipantMapper {

    static List<ActivityStudentParticipantModel> toModel(List<ActivityStudentParticipant> record){
        List<ActivityStudentParticipantModel> resultData = new ArrayList<>();
        record.forEach((entity) -> resultData.add(
            new ActivityStudentParticipantModel(
                    entity.getId(),
                    null,
                    entity.getStudentId(),
                    entity.getGradeId(),
                    entity.getStudentName(),
                    null,
                    entity.getGradeName(),
                    entity.getAmountRequired(),
                    entity.getAmountPaid(),
                    entity.getAmountRequired() - entity.getAmountPaid(),
                    entity.getPaymentStatus(),
                    entity.getLastPaymentDate()
            )));
        return resultData;
    }

    static List<ActivityStudentParticipant> convertModelsToEntities(List<ActivityStudentParticipantModel> record){
        List<ActivityStudentParticipant> resultData = new ArrayList<>();
        record.forEach((entity) -> resultData.add(
                new ActivityStudentParticipant(
                        entity.id(),
                        entity.activityId(),
                        entity.studentId(),
                        entity.gradeId(),
                        entity.studentName(),
                        entity.studentNis(),
                        entity.gradeName(),
                        entity.amountRequired(),
                        entity.amountPaid(),
                        entity.paymentStatus(),
                        entity.lastPaymentDate()
                )));
        return resultData;
    }
}
