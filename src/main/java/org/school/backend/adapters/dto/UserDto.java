package org.school.backend.adapters.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    String name;
    String role;
    String email;
    String password;
}
