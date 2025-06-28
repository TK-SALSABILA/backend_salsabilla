package org.school.backend.application.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record StudentDetailsDto(
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
        GradeDto academic
) implements Serializable {
}
