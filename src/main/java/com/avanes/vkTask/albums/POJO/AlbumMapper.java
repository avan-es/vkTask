package com.avanes.vkTask.albums.POJO;



public enum AlbumMapper {
    INSTANT;

    public Album albumsToAlbumsPost (AlbumFull albums) {
        return new Album(albums.getUserId(), albums.getTitle());
    }
}
