package org.school.backend.domain.model;

import java.io.Serializable;
import java.util.UUID;

public record UserModel(
        UUID id,
        String name,
        String password,
        String email,
        String role
) implements Serializable {

    public UserModel(
            String name,
            String password,
            String email,
            String role
    ){
        this(
                null,
                name,
                password,
                email,
                role
        );
    }
}
