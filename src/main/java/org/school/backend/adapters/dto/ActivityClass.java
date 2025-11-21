package org.school.backend.adapters.dto;

import lombok.*;

import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityClass {
    public UUID gradeId;
    public String gradeName;
}
