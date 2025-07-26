package org.school.backend.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

public record StudentModel(
        UUID id,
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
        StudentGradeModel gradeClass,
        ParentModel parent
) implements Serializable {

    public StudentModel(
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
            StudentGradeModel gradeClass,
            ParentModel parent
    ){
        this(
                null,
                fullName,
                nickName,
                nik,
                gender,
                dateBirth,
                birthOrder,
                tribe,
                address,
                height,
                weight,
                gradeClass,
                parent
        );
    }
}
