package com.avanes.vkTask.admin.service;

import com.avanes.vkTask.admin.dto.UserCreatDto;
import com.avanes.vkTask.admin.dto.UserShortDto;

import java.util.List;

public interface UserService {
    UserShortDto getUser(Long id);

    List<UserShortDto> getUsers();

    UserShortDto addUser(UserCreatDto user);

    void deleteUser(Long id);
}
