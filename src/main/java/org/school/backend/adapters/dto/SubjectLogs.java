package org.school.backend.adapters.dto;


import lombok.*;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectLogs {
   public UUID id;
   public String subjectName;
   public String subjectCode;
   public String gradeLevel;
   public Boolean isMandatory;
   public String description;


}
