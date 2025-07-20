package org.school.backend.application.mappers;

import org.school.backend.application.dto.GradeDto;
import org.school.backend.application.dto.StudentGradeDto;
import org.school.backend.application.dto.response.SubjectLogsDto;
import org.school.backend.domain.model.GradeModel;
import org.school.backend.domain.model.StudentGradeModel;
import org.school.backend.domain.model.GradeModel;

import java.util.ArrayList;
import java.util.List;

public class GradeMapper {
    public static GradeDto toDto(GradeModel entity){
        return new GradeDto(entity);
    }
    public static List<GradeDto> toListDto(List<GradeModel> entities) {
        List<GradeDto> gradeRecordDto = new ArrayList<>();
        entities.forEach((entity) -> gradeRecordDto.add(new GradeDto(entity)));
        return gradeRecordDto;
    }
    public static GradeModel toGradeModel(GradeDto record){
        return new GradeModel(
                record.id(),
                record.gradeLevel()
        );
    }
}
