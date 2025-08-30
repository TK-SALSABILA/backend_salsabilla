package org.school.backend.adapters.datasources.repository.impl;

import org.school.backend.adapters.configuration.ApplicationConfigProperties;
import org.school.backend.adapters.datasources.repository.JpaUserRepository;
import org.school.backend.adapters.datasources.repository.UserRepository;
import org.school.backend.adapters.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {
    final ApplicationConfigProperties applicationConfigProperties;
    final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(final  ApplicationConfigProperties applicationConfigProperties, final JpaUserRepository jpaUserRepository){
        this.applicationConfigProperties = applicationConfigProperties;
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public Optional<UserDto> findByName(String name) {
        UserDto user;
        switch (applicationConfigProperties.getDatabaseDefault().toLowerCase()) {
            case "postgres" -> {
                return jpaUserRepository.findByName(name)
                        .map(userJpa -> new UserDto(
                                userJpa.getName(),
                                userJpa.getRole(),
                                userJpa.getPassword()
                        ));
            }
            default -> throw new IllegalArgumentException("Unsupported database: "
                    + applicationConfigProperties.getDatabaseDefault());
        }
    }
}
