package com.avanes.vkTask.albums.service;

import com.avanes.vkTask.albums.POJO.Album;
import com.avanes.vkTask.albums.POJO.AlbumFull;

public interface AlbumService {
    AlbumFull getAlbum(Long id);

    AlbumFull[] getAlbums();

    Album addAlbum(Album album);

    AlbumFull patchAlbum(Album album, Long id);

    void deleteAlbum(Long id);
}
