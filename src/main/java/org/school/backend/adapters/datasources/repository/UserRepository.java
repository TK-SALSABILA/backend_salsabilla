package org.school.backend.adapters.datasources.repository;

import org.school.backend.adapters.dto.UserDto;

import java.util.Optional;

public interface UserRepository {
    Optional<UserDto> findByName(String name);
}
