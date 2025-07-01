package org.school.backend.adapters.mapper;

import org.school.backend.application.dto.ParentDto;
import org.school.backend.domain.model.ParentModel;

public class ParentMapper {
    public  static ParentModel toModel(ParentDto entities){
        return new ParentModel(
                entities.id(),
                entities.fatherName(),
                entities.fatherDateBirth(),
                entities.fatherNik(),
                entities.fatherEducation(),
                entities.fatherJob(),
                entities.motherName(),
                entities.motherDateBirth(),
                entities.motherNik(),
                entities.motherEducation(),
                entities.phone(),
                entities.fullAddress(),
                entities.postalCode()
        );
    }
}
