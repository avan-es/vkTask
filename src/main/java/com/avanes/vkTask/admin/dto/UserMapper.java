package com.avanes.vkTask.admin.dto;

import com.avanes.vkTask.constants.Roles;
import com.avanes.vkTask.admin.entity.UserEntity;

public enum UserMapper {
    INSTANT;

    public UserShortDto toUserShortDto (UserEntity userEntity) {
        UserShortDto result = new UserShortDto();
        result.setEmail(userEntity.getEmail());
        result.setRole(Roles.valueOf(userEntity.getRole()));
        result.setId(userEntity.getId());
        return result;

    }

    public UserEntity userCreatDtoToUser(UserCreatDto user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setRole(user.getRole().name());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }
}
