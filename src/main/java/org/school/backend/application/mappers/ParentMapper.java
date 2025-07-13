package org.school.backend.application.mappers;

import org.school.backend.application.dto.ParentDto;
import org.school.backend.domain.model.ParentModel;

import java.util.ArrayList;
import java.util.List;

public class ParentMapper {
    public static ParentDto toDto(ParentModel entity){
        return new ParentDto(entity);
    }

    public static List<ParentDto> toListDto(List<ParentModel> entities){
        List<ParentDto> parentModelDto = new ArrayList<>();
        entities.forEach((entity) -> parentModelDto.add(new ParentDto(entity)));
        return parentModelDto;
    }
}
