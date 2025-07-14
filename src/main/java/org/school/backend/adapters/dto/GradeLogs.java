package org.school.backend.adapters.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GradeLogs {
    public UUID id;
    public String gradeLevel;
}
