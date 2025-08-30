package org.school.backend.adapters.mapper;

import org.school.backend.adapters.dto.UserDto;
import org.school.backend.domain.model.UserModel;

import java.util.Optional;

public interface UserMapper {

    static UserDto convertEntityToModel(final UserModel entity){
        return UserDto.builder()
                .name(entity.name())
                .role(entity.role())
                .email(entity.email())
                .password(entity.password())
                .build();
    }

    static Optional<UserModel> convertModelsToEntity(final Optional<UserDto> entity){
        if(entity.isPresent()){
            return Optional.of(new UserModel(
                    entity.get().getName(),
                    entity.get().getPassword(),
                    entity.get().getEmail(),
                    entity.get().getRole()
            ));
        }else {
            return Optional.empty();
        }
    };
}
