package org.school.backend.adapters.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentLogs {

    public UUID id;
    public String fullName;
    public String nickName;
    public String nik;
    public String gender;
    public LocalDateTime dateBirth;
    public String birthOrder;
}
