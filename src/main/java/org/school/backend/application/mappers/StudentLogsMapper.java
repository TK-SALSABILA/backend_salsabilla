package org.school.backend.application.mappers;

import org.school.backend.application.dto.response.StudentsLogsOutputDto;
import org.school.backend.domain.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class StudentLogsMapper {
    public static StudentsLogsOutputDto toDto(StudentModel entity) {
        return new StudentsLogsOutputDto(entity);
    }

    public static List<StudentsLogsOutputDto> toListDto(List<StudentModel> entities) {
        List<StudentsLogsOutputDto> studerRecordDto = new ArrayList<>();
        entities.forEach((entity) -> studerRecordDto.add(new StudentsLogsOutputDto(entity)));
        return studerRecordDto;
    }
}
