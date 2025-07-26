package org.school.backend.adapters.dto;


import lombok.*;
import org.school.backend.application.dto.ParentDto;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
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
    public ParentLogs parent;
}
