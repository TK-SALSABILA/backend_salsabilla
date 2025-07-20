package org.school.backend.adapters.mapper;

import org.school.backend.adapters.dto.ParentLogs;
import org.school.backend.domain.model.ParentModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ParentLogsMapper {

    static ParentLogs convertEntityToModel(final ParentModel entity) {
        return ParentLogs.builder()
                .fatherName(entity.fatherName())
                .fatherDateBirth(entity.fatherDateBirth())
                .fatherNik(entity.fatherNik())
                .fatherEducation(entity.fatherEducation())
                .fatherJob(entity.fatherJob())
                .fatherCitizen(entity.fatherCitizen())
                .fatherIncome(entity.fatherIncome())
                .fatherAddress(entity.fatherAddress())
                .fatherPhone(entity.fatherPhone())
                .motherName(entity.motherName())
                .motherDateBirth(entity.motherDateBirth())
                .motherNik(entity.motherNik())
                .motherEducation(entity.motherEducation())
                .motherCitizen(entity.motherCitizen())
                .motherIncome(entity.motherIncome())
                .motherAddress(entity.motherAddress())
                .motherPhone(entity.motherPhone()).build();
    }

    static List<ParentModel> convertModelsToEntity(final List<ParentLogs> recordEntities) {
        List<ParentModel> parentRecordEntities = new ArrayList<>();
        recordEntities.forEach((entity) -> parentRecordEntities.add(new ParentModel(
                entity.getFatherName(),
                entity.getFatherDateBirth(),
                entity.getFatherNik(),
                entity.getFatherEducation(),
                entity.getFatherJob(),
                entity.getFatherCitizen(),
                entity.getFatherIncome(),
                entity.getFatherAddress(),
                entity.getFatherPhone(),
                entity.getMotherName(),
                entity.getMotherDateBirth(),
                entity.getMotherNik(),
                entity.getMotherEducation(),
                entity.getMotherCitizen(),
                entity.getMotherIncome(),
                entity.getMotherAddress(),
                entity.getMotherPhone()
        )));
        return parentRecordEntities;
    }

    static Optional<ParentModel> convertModelToEntity(Optional<ParentLogs> entity) {
        if(entity.isPresent()) {
            return Optional.of(new ParentModel(
                    entity.get().getFatherName(),
                    entity.get().getFatherDateBirth(),
                    entity.get().getFatherNik(),
                    entity.get().getFatherEducation(),
                    entity.get().getFatherJob(),
                    entity.get().getFatherCitizen(),
                    entity.get().getFatherIncome(),
                    entity.get().getFatherAddress(),
                    entity.get().getFatherPhone(),
                    entity.get().getMotherName(),
                    entity.get().getMotherDateBirth(),
                    entity.get().getMotherNik(),
                    entity.get().getMotherEducation(),
                    entity.get().getMotherCitizen(),
                    entity.get().getMotherIncome(),
                    entity.get().getMotherAddress(),
                    entity.get().getMotherPhone()
            ));
        } else {
            return Optional.empty();
        }
    }
}
