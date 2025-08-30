package org.school.backend.application.dto.request;

import java.io.Serializable;

public record LoginReqDto(
        String name,
        String password
) implements Serializable {
}
