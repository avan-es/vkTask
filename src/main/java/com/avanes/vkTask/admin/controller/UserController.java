package com.avanes.vkTask.admin.controller;

import com.avanes.vkTask.admin.dto.UserCreatDto;
import com.avanes.vkTask.admin.dto.UserShortDto;
import com.avanes.vkTask.admin.service.UserService;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("avan/admin")
@Validated
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public UserShortDto getUserById(
            @PositiveOrZero @PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public List<UserShortDto> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public UserShortDto addUser(
            @RequestBody UserCreatDto user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(
            @PositiveOrZero @PathVariable Long id) {
        userService.deleteUser(id);
    }
}
