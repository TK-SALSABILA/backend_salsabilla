package org.school.backend.adapters.datasources.impl;

import org.school.backend.adapters.datasources.UserDataSource;
import org.school.backend.adapters.datasources.repository.UserRepository;
import org.school.backend.adapters.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDataSourceImpl implements UserDataSource {
    private final UserRepository userRepository;

    public UserDataSourceImpl(final UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public Optional<UserDto> findByName(String name) {
        return userRepository.findByName(name);
    }
}
