package com.avanes.vkTask.albums.controller;

import com.avanes.vkTask.albums.POJO.AlbumFull;
import com.avanes.vkTask.albums.POJO.Album;
import com.avanes.vkTask.albums.service.AlbumService;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("avan/albums")
@Validated
@Slf4j
public class AlbumController {

    private final AlbumService albumService;


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ALBUMS')")
    @ResponseStatus(HttpStatus.OK)
    public AlbumFull getAlbumById(
            @PositiveOrZero @PathVariable Long id) {
        return albumService.getAlbum(id);
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ALBUMS')")
    @ResponseStatus(HttpStatus.OK)
    public AlbumFull[] getAlbums() {
        return albumService.getAlbums();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ALBUMS')")
    @ResponseStatus(HttpStatus.CREATED)
    public AlbumFull addAlbum(
            @RequestBody Album album) {
        return albumService.addAlbum(album);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ALBUMS')")
    @ResponseStatus(HttpStatus.OK)
    public AlbumFull patchAlbum(
            @RequestBody Album album,
            @PositiveOrZero @PathVariable Long id) {
        return albumService.patchAlbum(album, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ALBUMS')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAlbum(
            @PositiveOrZero @PathVariable Long id) {
        albumService.deleteAlbum(id);
    }

}
