package org.school.backend.application.mappers;

import org.school.backend.application.dto.GradeDto;
import org.school.backend.domain.model.GradeModel;

import java.util.ArrayList;
import java.util.List;

public class GradeMapper {
    public static GradeDto toDto(GradeModel entity) {
        return new GradeDto(entity);
    }
    public static List<GradeDto> toListDto(List<GradeModel> entities) {
        List<GradeDto> graderModelDto = new ArrayList<>();
        entities.forEach((entity) -> graderModelDto.add(new GradeDto(entity)));
        return graderModelDto;
    }

    public static GradeModel toGradeModel(GradeDto dto) {
        return new GradeModel(dto.gradeLevel(), dto.academicYear());
    }
}
