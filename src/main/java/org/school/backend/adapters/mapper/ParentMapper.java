package org.school.backend.adapters.mapper;

import org.school.backend.application.dto.ParentDto;
import org.school.backend.domain.model.ParentModel;

import static org.school.backend.application.utils.DateTimeFormatterConfig.parseDate;

public class ParentMapper {
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
