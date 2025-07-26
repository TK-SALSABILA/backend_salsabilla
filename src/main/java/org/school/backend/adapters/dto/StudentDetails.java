package org.school.backend.adapters.dto;

import lombok.*;
import org.school.backend.application.dto.StudentGradeDto;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDetails {
    public UUID id;
    public String fullName;
    public String nickName;
    public String nik;
    public String gender;
    public LocalDateTime dateBirth;
    public String birthOrder;
    public String tribe;
    public String address;
    public String height;
    public String weight;
    public StudentGrade gradeClass;

}