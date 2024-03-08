package com.avanes.vkTask.albums.controller;

import com.avanes.vkTask.albums.POJO.Albums;
import com.avanes.vkTask.albums.POJO.AlbumsPost;
import com.avanes.vkTask.albums.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping("/{id}")
    public Albums getAlbumById(@PathVariable Integer id) {
        return albumService.getAlbum(id);
    }

    @GetMapping()
    public Albums[] getAlbums() {
        return albumService.getAlbums();
    }

    @PostMapping
    public Albums addAlbum(@RequestBody AlbumsPost album) {
        return albumService.addAlbum(album);
    }
    @PatchMapping("/{id}")
    public Albums patchAlbum(@RequestBody AlbumsPost album,
                             @PathVariable Integer id) {
        return albumService.patchAlbum(album, id);
    }

}
