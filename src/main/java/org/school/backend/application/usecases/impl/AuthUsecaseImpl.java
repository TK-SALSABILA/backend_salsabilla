package org.school.backend.application.usecases.impl;

import org.school.backend.application.dto.request.LoginReqDto;
import org.school.backend.application.dto.response.LoginResDto;
import org.school.backend.application.usecases.AuthUsecase;
import org.school.backend.domain.gateaway.UserGateway;
import org.school.backend.domain.model.UserModel;

import java.util.Optional;

public class AuthUsecaseImpl implements AuthUsecase {

    private final UserGateway userGateway;
    public AuthUsecaseImpl(final UserGateway userGateway){
        this.userGateway = userGateway;
    }
    @Override
    public Optional<LoginResDto> login(LoginReqDto record) {
        if(!userGateway.validateUser(record.name(),record.password())){
            throw new IllegalArgumentException("Invalid credentials");
        }

        Optional<UserModel> role = userGateway.findRoleByName(record.name());
        String token = jwtTokenProvider.generateToken(username, role);
        return new LoginResDto(token,role);
    }
}
