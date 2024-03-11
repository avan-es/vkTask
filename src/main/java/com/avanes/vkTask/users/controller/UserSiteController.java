package com.avanes.vkTask.users.controller;

import com.avanes.vkTask.users.POJO.UserSite;
import com.avanes.vkTask.users.POJO.UserSiteFull;
import com.avanes.vkTask.users.service.UserSiteService;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("avan/users")
@Validated
@Slf4j
public class UserSiteController {
    private final UserSiteService userSiteService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USERS')")
    @ResponseStatus(HttpStatus.OK)
    public UserSiteFull getAlbumById(
            @PositiveOrZero @PathVariable Long id) {
        return userSiteService.getUserSite(id);
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USERS')")
    @ResponseStatus(HttpStatus.OK)
    public UserSiteFull[] getAlbums() {
        return userSiteService.getUserSites();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USERS')")
    @ResponseStatus(HttpStatus.CREATED)
    public UserSiteFull addAlbum(
            @RequestBody UserSite userSite) {
        return userSiteService.addUserSite(userSite);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USERS')")
    @ResponseStatus(HttpStatus.OK)
    public UserSiteFull patchAlbum(
            @RequestBody UserSite userSite,
            @PositiveOrZero @PathVariable Long id) {
        return userSiteService.patchUserSite(userSite, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USERS')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(
            @PositiveOrZero @PathVariable Long id) {
        userSiteService.deleteUserSite(id);
    }
}
