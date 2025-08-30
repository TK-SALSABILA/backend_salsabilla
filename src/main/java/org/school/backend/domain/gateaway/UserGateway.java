package org.school.backend.domain.gateaway;

import org.school.backend.domain.model.UserModel;

import java.util.Optional;

public interface UserGateway {
    Boolean validateUser(String name,String password);
    Optional<UserModel> findRoleByName(String name);
}
