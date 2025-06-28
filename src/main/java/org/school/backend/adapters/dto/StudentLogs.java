package org.school.backend.adapters.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentLogs {

    public Integer id;
    public String fullName;
    public String nickName;
    public String nik;
    public String gender;
    public LocalDateTime dateBirth;
    public String birthOrder;
}
