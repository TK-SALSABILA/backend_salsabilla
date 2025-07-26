package org.school.backend.application.mappers;

import org.school.backend.application.dto.ParentDto;
import org.school.backend.domain.model.ParentModel;

import java.util.ArrayList;
import java.util.List;

import static org.school.backend.application.utils.DateTimeFormatterConfig.parseDate;

public class ParentMapper {
    public static ParentDto toDto(ParentModel entity){
        return new ParentDto(entity);
    }

    public static List<ParentDto> toListDto(List<ParentModel> entities){
        List<ParentDto> parentModelDto = new ArrayList<>();
        entities.forEach((entity) -> parentModelDto.add(new ParentDto(entity)));
        return parentModelDto;
    }

    public static ParentModel toModel(ParentDto entities){
        return new ParentModel(
                entities.id(),
                entities.fatherName(),
                parseDate(entities.fatherDateBirth()),
                entities.fatherNik(),
                entities.fatherEducation(),
                entities.fatherJob(),
                entities.fatherCitizen(),
                entities.fatherIncome(),
                entities.fatherAddress(),
                entities.fatherPhone(),
                entities.motherName(),
                parseDate(entities.motherDateBirth()),
                entities.motherNik(),
                entities.motherEducation(),
                entities.motherCitizen(),
                entities.motherIncome(),
                entities.motherAddress(),
                entities.motherPhone()
        );
    }
}
