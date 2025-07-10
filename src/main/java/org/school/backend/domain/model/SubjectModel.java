package org.school.backend.domain.model;

import java.io.Serializable;
import java.util.UUID;

public record SubjectModel(
        UUID id,
        String subjectName,
        String subjectCode,
        String gradeLevel,
        Boolean isMandatory,
        String description
) implements Serializable {
    public SubjectModel(
            String subjectName,
            String subjectCode,
            String gradeLevel,
            Boolean isMandatory,
            String description
    ){
        this(
                null,
                subjectName,
                subjectCode,
                gradeLevel,
                isMandatory,
                description
        );
    }
}
