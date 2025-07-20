package org.school.backend.adapters.mapper;

import org.school.backend.adapters.dto.SubjectLogs;
import org.school.backend.domain.model.SubjectModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface SubjectLogMapper {

    static SubjectLogs convertEntityToModel(final SubjectModel entity) {
        return SubjectLogs.builder()
                .subjectName(entity.subjectName())
                .subjectCode(entity.subjectCode())
                .gradeLevel(entity.gradeLevel())
                .isMandatory(entity.isMandatory())
                .description(entity.description()).build();
    }

    static List<SubjectModel> convertModelsToEntity(final List<SubjectLogs> recordEntities) {
        List<SubjectModel> subjectReocrdEntities = new ArrayList<>();
        recordEntities.forEach((entity) -> subjectReocrdEntities.add(new SubjectModel(
                entity.getId(),
                entity.getSubjectName(),
                entity.getSubjectCode(),
                entity.getGradeLevel(),
                entity.getIsMandatory(),
                entity.getDescription()
        )));
        return subjectReocrdEntities;
    }

    static Optional<SubjectModel> convertModelToEntity(Optional<SubjectLogs> entity) {
        if(entity.isPresent()) {
            return Optional.of(new SubjectModel(
                    entity.get().getSubjectName(),
                    entity.get().getSubjectCode(),
                    entity.get().getGradeLevel(),
                    entity.get().getIsMandatory(),
                    entity.get().getDescription()
            ));
        } else {
            return Optional.empty();
        }
    }
}
