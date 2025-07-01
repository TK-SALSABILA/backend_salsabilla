package org.school.backend.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public record StudentModel(
        Integer id,
        String fullName,
        String nickName,
        String nik,
        String gender,
        LocalDateTime dateBirth,
        String birthOrder,
        String tribe,
        String address,
        String height,
        String weight,
        GradeModel gradeClass,
        ParentModel parent
) implements Serializable {
}
