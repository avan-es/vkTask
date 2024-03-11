package com.avanes.vkTask.admin.service;

import com.avanes.vkTask.ApiError.exception.ConflictException;
import com.avanes.vkTask.ApiError.exception.NotFoundException;
import com.avanes.vkTask.admin.dto.UserCreatDto;
import com.avanes.vkTask.admin.dto.UserShortDto;
import com.avanes.vkTask.admin.entity.UserEntity;
import com.avanes.vkTask.admin.dto.UserMapper;
import com.avanes.vkTask.admin.storage.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public UserShortDto getUser(Long id) {
        return UserMapper.INSTANT.toUserShortDto(userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Пользователь с ID = " + id + " не найден.")));
    }

    @Override
    public List<UserShortDto> getUsers() {
        return userRepository.getAllUsers().stream()
                .map(user -> UserMapper.INSTANT.toUserShortDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserShortDto addUser(UserCreatDto user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(5);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (!isUserPresentByEmail(user.getEmail())) {
            UserEntity userEntity = userRepository.save(UserMapper.INSTANT.userCreatDtoToUser(user));
            return UserMapper.INSTANT.toUserShortDto(userEntity);
        } else {
            throw new ConflictException("Данный адрес почты уже занят.");
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private boolean isUserPresentByEmail(String email) {
        return userRepository.findFirstByEmail(email) != null;
    }


}
