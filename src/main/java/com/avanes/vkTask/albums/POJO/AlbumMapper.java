package com.avanes.vkTask.albums.POJO;



public enum AlbumMapper {
    INSTANT;

    public AlbumsPost albumsToAlbumsPost (Albums albums) {
        return new AlbumsPost(albums.getId(), albums.getTitle());
    }
}
