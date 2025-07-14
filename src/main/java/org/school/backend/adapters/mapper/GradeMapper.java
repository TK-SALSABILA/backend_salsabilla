package org.school.backend.adapters.mapper;

import org.school.backend.adapters.dto.GradeLogs;
import org.school.backend.application.dto.GradeDto;
import org.school.backend.domain.model.GradeModel;
import org.school.backend.domain.model.GradeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface GradeMapper {

    public static GradeModel toModel(GradeDto record){
        return new GradeModel(
                record.id(),
                record.gradeLevel()
        );
    }

    static GradeLogs convertEntityToModel(final GradeModel entity) {
        return GradeLogs.builder()
                .gradeLevel(entity.gradeLevel()).build();

    }

    static List<GradeModel> convertModelsToEntity(final List<GradeLogs> recordEntities) {
        List<GradeModel> subjectReocrdEntities = new ArrayList<>();
        recordEntities.forEach((entity) -> subjectReocrdEntities.add(new GradeModel(
                entity.getId(),
                entity.getGradeLevel()
        )));
        return subjectReocrdEntities;
    }

    static Optional<GradeModel> convertModelToEntity(Optional<GradeLogs> entity) {
        if(entity.isPresent()) {
            return Optional.of(new GradeModel(
                    entity.get().getGradeLevel()
            ));
        } else {
            return Optional.empty();
        }
    }
}
