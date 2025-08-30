package org.school.backend.application.dto.response;

import java.io.Serializable;

public record LoginResDto(
        String token,
        String role
) implements Serializable {

    public LoginResDto(String token,String role){
        this.token = token;
        this.role = role;
    }
}
