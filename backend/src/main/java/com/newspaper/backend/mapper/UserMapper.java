package com.newspaper.backend.mapper;

import com.newspaper.backend.user.UserEntity;
import com.newspaper.backend.user.UserDto;

public class UserMapper implements DefaultMapper<UserDto, UserEntity> {
    @Override
    public UserEntity dtoToEntity(UserDto dto) {
        return new UserEntity(dto.getEmail(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getPassword());
    }

    @Override
    public void updateEntity(UserEntity entity, UserDto dto) {
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPassword(dto.getPassword());
    }
}
