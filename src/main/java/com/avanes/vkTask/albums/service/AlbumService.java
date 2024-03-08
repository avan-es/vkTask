package com.avanes.vkTask.albums.service;

import com.avanes.vkTask.albums.POJO.AlbumMapper;
import com.avanes.vkTask.albums.POJO.Albums;
import com.avanes.vkTask.albums.POJO.AlbumsPost;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.avanes.vkTask.constants.TaskConstants.*;

@Service
@RequiredArgsConstructor
public class AlbumService {

    public Albums getAlbum(Integer id) {
        StringBuilder builder = getData(ALBUMS_URL + "/" + id);
        Gson g = new Gson();
        return g.fromJson(builder.toString(), Albums.class);
    }

    public Albums[] getAlbums() {
        StringBuilder builder = getData(ALBUMS_URL);
        Gson g = new Gson();
        return g.fromJson(builder.toString(), Albums[].class);
    }

    public Albums addAlbum(AlbumsPost album) {
        Albums result = new Albums();
        Gson gson = new Gson();
        String json = gson.toJson(album);
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ALBUMS_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            result = gson.fromJson(response.body(), Albums.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    private StringBuilder getData(String adress) {
        StringBuilder builder = new StringBuilder();
        try {
            URL url = new URL(adress);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Ошибка запроса. HTTP error code: " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            while (((output = br.readLine()) != null)) {
                builder.append(output);
            }
            conn.disconnect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return builder;
    }

    public Albums patchAlbum(AlbumsPost album, Integer id) {
        Albums oldAlbum = getAlbum(id);
        boolean isChanged = false;
        if ((album.getTitle() != null) && (!album.getTitle().equals(oldAlbum.getTitle()))) {
            oldAlbum.setTitle(album.getTitle());
            isChanged = true;
        }
        if ((album.getUserId() != null) && (!album.getUserId().equals(oldAlbum.getUserId()))) {
            oldAlbum.setUserId(album.getUserId());
            isChanged = true;
        }
        if (isChanged) {
            return addAlbum(AlbumMapper.INSTANT.albumsToAlbumsPost(oldAlbum));
        } else {
            System.out.println("Обновление не выполнено. Новые данные соответствуют старым.");
        }
        return oldAlbum;
    }
}
