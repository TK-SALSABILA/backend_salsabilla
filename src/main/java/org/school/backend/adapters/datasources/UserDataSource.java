package org.school.backend.adapters.datasources;

import org.school.backend.adapters.dto.UserDto;

import java.util.Optional;

public interface UserDataSource {
    Optional<UserDto> findByName(String name);
}
