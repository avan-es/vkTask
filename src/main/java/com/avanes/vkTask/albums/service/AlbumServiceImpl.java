package com.avanes.vkTask.albums.service;

import com.avanes.vkTask.albums.POJO.AlbumFull;
import com.avanes.vkTask.albums.POJO.Album;
import com.avanes.vkTask.utils.GetData;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.avanes.vkTask.constants.CrudOperations.GET;
import static com.avanes.vkTask.constants.TaskConstants.*;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    @Override
    @Cacheable(cacheNames = "albumsById")
    public AlbumFull getAlbum(Long id) {
        StringBuilder builder = GetData.INSTANCE.getDataFromServer(ALBUMS_URL + "/" + id, GET);
        Gson g = new Gson();
        AlbumFull result = g.fromJson(builder.toString(), AlbumFull.class);
        return result;
    }

    @Override
    public AlbumFull[] getAlbums() {
        StringBuilder builder = GetData.INSTANCE.getDataFromServer(ALBUMS_URL, GET);
        Gson g = new Gson();
        return g.fromJson(builder.toString(), AlbumFull[].class);
    }

    @Override
    public AlbumFull addAlbum(Album album) {
        AlbumFull result = new AlbumFull();
        Gson gson = new Gson();
        try {
            HttpResponse<String> response = GetData.INSTANCE.addData(album, ALBUMS_URL);
            result = gson.fromJson(response.body(), AlbumFull.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public AlbumFull patchAlbum(Album album, Long id) {
        AlbumFull oldAlbum = getAlbum(id);
        boolean isChanged = false;
        Gson gson = new Gson();
        if ((album.getTitle() != null) && (!album.getTitle().equals(oldAlbum.getTitle()))) {
            oldAlbum.setTitle(album.getTitle());
            isChanged = true;
        }
        if ((album.getUserId() != null) && (!album.getUserId().equals(oldAlbum.getUserId()))) {
            oldAlbum.setUserId(album.getUserId());
            isChanged = true;
        }
        if (isChanged) {
            try {
                HttpResponse<String> response = GetData.INSTANCE.patchData(oldAlbum, ALBUMS_URL + "/" + id);
                oldAlbum = gson.fromJson(response.body(), AlbumFull.class);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Обновление не выполнено. Новые данные соответствуют старым.");
        }
        return oldAlbum;
    }

    @Override
    public void deleteAlbum(Long id) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(ALBUMS_URL + "/" + id))
                .DELETE()
                .build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
