package org.school.backend.application.mappers;

import org.school.backend.application.dto.StudentGradeDto;
import org.school.backend.domain.model.StudentGradeModel;

import java.util.ArrayList;
import java.util.List;

public class StudentGradeMapper {
    public static StudentGradeDto toDto(StudentGradeModel entity) {
        return new StudentGradeDto(entity);
    }
    public static List<StudentGradeDto> toListDto(List<StudentGradeModel> entities) {
        List<StudentGradeDto> graderModelDto = new ArrayList<>();
        entities.forEach((entity) -> graderModelDto.add(new StudentGradeDto(entity)));
        return graderModelDto;
    }

    public static StudentGradeModel toGradeModel(StudentGradeDto dto) {
        return new StudentGradeModel(
                dto.academicYear(),
                dto.isCurrent(),
                GradeMapper.toGradeModel(dto.gradeLog())
        );
    }
}
