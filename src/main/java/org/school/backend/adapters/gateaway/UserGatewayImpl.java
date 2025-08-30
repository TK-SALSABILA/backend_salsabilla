package org.school.backend.adapters.gateaway;

import org.school.backend.adapters.datasources.UserDataSource;
import org.school.backend.domain.gateaway.UserGateway;
import org.school.backend.domain.model.UserModel;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.school.backend.adapters.mapper.UserMapper.convertModelsToEntity;

@Component
public class UserGatewayImpl implements UserGateway {

    private final UserDataSource userDataSource;
    private PasswordEncoder passwordEncoder;

    public UserGatewayImpl(final UserDataSource userDataSource,final PasswordEncoder passwordEncoder){
        this.userDataSource = userDataSource;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Optional<UserModel> findRoleByName(String name) {
        return convertModelsToEntity(userDataSource.findByName(name));
    }

    @Override
    public Boolean validateUser(String name, String password) {
        Optional<UserModel> userOpt = convertModelsToEntity(userDataSource.findByName(name));
        if (userOpt.isEmpty()) return false;
        return passwordEncoder.matches(password, userOpt.get().getPassword());
    }
}
