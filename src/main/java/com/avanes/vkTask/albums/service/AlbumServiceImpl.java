package com.avanes.vkTask.albums.service;

import com.avanes.vkTask.albums.POJO.AlbumFull;
import com.avanes.vkTask.albums.POJO.Album;
import com.avanes.vkTask.constants.CrudOperations;
import com.avanes.vkTask.log.service.LogDataService;
import com.avanes.vkTask.utils.GetData;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

import static com.avanes.vkTask.constants.CrudOperations.GET;
import static com.avanes.vkTask.constants.TaskConstants.*;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

    private final LogDataService log;

    @Override
    @Cacheable(cacheNames = "albumsById")
    public AlbumFull getAlbum(Long id) {
        StringBuilder builder = GetData.INSTANCE.getDataFromServer(ALBUMS_URL + "/" + id, GET);
        Gson g = new Gson();
        AlbumFull result = g.fromJson(builder.toString(), AlbumFull.class);
        log.addLog(LocalDateTime.now(), ALBUMS_URL + "/" + id, CrudOperations.GET.name(), "SUCCESS");
        return result;
    }

    @Override
    public AlbumFull[] getAlbums() {
        StringBuilder builder = GetData.INSTANCE.getDataFromServer(ALBUMS_URL, GET);
        Gson g = new Gson();
        log.addLog(LocalDateTime.now(), ALBUMS_URL, CrudOperations.GET.name(), "SUCCESS");
        return g.fromJson(builder.toString(), AlbumFull[].class);
    }

    @Override
    public AlbumFull addAlbum(Album album) {
        AlbumFull result = new AlbumFull();
        Gson gson = new Gson();
        try {
            HttpResponse<String> response = GetData.INSTANCE.addData(album, ALBUMS_URL);
            result = gson.fromJson(response.body(), AlbumFull.class);
            log.addLog(LocalDateTime.now(), ALBUMS_URL, CrudOperations.POST.name(), "SUCCESS");
        } catch (IOException | InterruptedException e) {
            log.addLog(LocalDateTime.now(), ALBUMS_URL, CrudOperations.POST.name(), "FAIL");
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
                log.addLog(LocalDateTime.now(), ALBUMS_URL, CrudOperations.PATCH.name() + "/" + id, "SUCCESS");
            } catch (IOException | InterruptedException e) {
                log.addLog(LocalDateTime.now(), ALBUMS_URL, CrudOperations.PATCH.name() + "/" + id, "FAIL");
                throw new RuntimeException(e);
            }
        } else {
            log.addLog(LocalDateTime.now(), ALBUMS_URL, CrudOperations.PATCH.name() + "/" + id, "FAIL: Данные не были обновлены.");
        }
        return oldAlbum;
    }

    @Override
    public void deleteAlbum(Long id) {
        GetData.INSTANCE.deleteData(ALBUMS_URL + "/" + id);
        log.addLog(LocalDateTime.now(), ALBUMS_URL, CrudOperations.PATCH.name() + "/" + id, "SUCCESS");

    }
}
