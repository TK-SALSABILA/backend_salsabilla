package org.school.backend.application.usecases;

import org.school.backend.application.dto.request.LoginReqDto;
import org.school.backend.application.dto.response.LoginResDto;

import java.util.Optional;

public interface AuthUsecase {
    Optional<LoginResDto> login(LoginReqDto record);
}
