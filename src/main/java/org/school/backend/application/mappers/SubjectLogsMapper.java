package org.school.backend.application.mappers;

import org.school.backend.application.dto.response.StudentsLogsOutputDto;
import org.school.backend.application.dto.response.SubjectLogsDto;
import org.school.backend.domain.model.SubjectModel;

import java.util.ArrayList;
import java.util.List;

public class SubjectLogsMapper {
    public static SubjectLogsDto toDto(SubjectModel entity) {
        return new SubjectLogsDto(entity);
    }

    public static List<SubjectLogsDto> toListDto(List<SubjectModel> entities) {
        List<SubjectLogsDto> subjectRecordDto = new ArrayList<>();
        entities.forEach((entity) -> subjectRecordDto.add(new SubjectLogsDto(entity)));
        return subjectRecordDto;
    }
}
