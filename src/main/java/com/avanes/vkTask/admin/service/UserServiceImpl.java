package com.avanes.vkTask.admin.service;

import com.avanes.vkTask.ApiError.exception.ConflictException;
import com.avanes.vkTask.ApiError.exception.NotFoundException;
import com.avanes.vkTask.admin.dto.UserCreatDto;
import com.avanes.vkTask.admin.dto.UserShortDto;
import com.avanes.vkTask.admin.entity.UserEntity;
import com.avanes.vkTask.admin.dto.UserMapper;
import com.avanes.vkTask.admin.storage.UserRepository;
import com.avanes.vkTask.constants.CrudOperations;
import com.avanes.vkTask.log.service.LogDataService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.avanes.vkTask.constants.TaskConstants.ADMIN_URL;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final LogDataService log;

    @Override
    public UserShortDto getUser(Long id) {
        log.addLog(LocalDateTime.now(), ADMIN_URL + "/" + id, CrudOperations.GET.name(), "SUCCESS");
        return UserMapper.INSTANT.toUserShortDto(userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Пользователь с ID = " + id + " не найден.")));
    }

    @Override
    public List<UserShortDto> getUsers() {
        log.addLog(LocalDateTime.now(), ADMIN_URL, CrudOperations.GET.name(), "SUCCESS");
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
            log.addLog(LocalDateTime.now(), ADMIN_URL, CrudOperations.POST.name(), "SUCCESS");
            return UserMapper.INSTANT.toUserShortDto(userEntity);
        } else {
            log.addLog(LocalDateTime.now(), ADMIN_URL, CrudOperations.GET.name(), "FAIL: Данный адрес почты уже занят.");
            throw new ConflictException("Данный адрес почты уже занят.");
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        log.addLog(LocalDateTime.now(), ADMIN_URL, CrudOperations.DELETE.name(), "SUCCESS");

    }

    private boolean isUserPresentByEmail(String email) {
        return userRepository.findFirstByEmail(email) != null;
    }


}
