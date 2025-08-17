package org.school.backend.adapters.mapper;

import org.school.backend.adapters.dto.GradeLogs;
import org.school.backend.domain.model.GradeModel;

import java.util.ArrayList;
import java.util.List;

public interface GradeMapper {

    static GradeLogs convertEntityToModel(final GradeModel entity) {
        return GradeLogs.builder()
                .id(entity.id())
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

    static GradeModel convertModelToEntity(GradeLogs entity) {
            return new GradeModel(
                    entity.getId(),
                    entity.getGradeLevel()
            );

    }
}
