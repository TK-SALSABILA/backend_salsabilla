package org.school.backend.application.mappers;

import org.school.backend.application.dto.StudentDetailsDto;
import org.school.backend.domain.model.StudentModel;

public class StudentDetailMapper {
    public static StudentDetailsDto toDto(StudentModel entity){
        return new StudentDetailsDto(entity);
    }
}
