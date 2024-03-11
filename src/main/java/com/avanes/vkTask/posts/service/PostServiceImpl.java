package com.avanes.vkTask.posts.service;

import com.avanes.vkTask.constants.CrudOperations;
import com.avanes.vkTask.log.service.LogDataService;
import com.avanes.vkTask.posts.POJO.Post;
import com.avanes.vkTask.posts.POJO.PostFull;
import com.avanes.vkTask.utils.GetData;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

import static com.avanes.vkTask.constants.CrudOperations.GET;
import static com.avanes.vkTask.constants.TaskConstants.POSTS_URL;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final LogDataService log;

    @Override
    @Cacheable(cacheNames = "postsById")
    public PostFull getPost(Long id) {
        StringBuilder builder = GetData.INSTANCE.getDataFromServer(POSTS_URL + "/" + id, GET);
        Gson g = new Gson();
        log.addLog(LocalDateTime.now(), POSTS_URL + "/" + id, CrudOperations.GET.name(), "SUCCESS");
        return g.fromJson(builder.toString(), PostFull.class);
    }

    @Override
    public PostFull[] getPosts() {
        StringBuilder builder = GetData.INSTANCE.getDataFromServer(POSTS_URL, GET);
        Gson g = new Gson();
        log.addLog(LocalDateTime.now(), POSTS_URL, CrudOperations.GET.name(), "SUCCESS");
        return g.fromJson(builder.toString(), PostFull[].class);
    }

    @Override
    public PostFull addPost(Post post) {
        PostFull result = new PostFull();
        Gson gson = new Gson();
        try {
            HttpResponse<String> response = GetData.INSTANCE.addData(post, POSTS_URL);
            result = gson.fromJson(response.body(), PostFull.class);
            log.addLog(LocalDateTime.now(), POSTS_URL, CrudOperations.POST.name(), "SUCCESS");
        } catch (IOException | InterruptedException e) {
            log.addLog(LocalDateTime.now(), POSTS_URL, CrudOperations.POST.name(), "FAIL");
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public PostFull patchPost(Post post, Long id) {
        PostFull oldPost = getPost(id);
        boolean isChanged = false;
        Gson gson = new Gson();
        if ((post.getTitle() != null) && (!post.getTitle().equals(oldPost.getTitle()))) {
            oldPost.setTitle(post.getTitle());
            isChanged = true;
        }
        if ((post.getUserId() != null) && (!post.getUserId().equals(oldPost.getUserId()))) {
            oldPost.setUserId(post.getUserId());
            isChanged = true;
        }
        if ((post.getBody() != null) && (!post.getBody().equals(oldPost.getBody()))) {
            oldPost.setBody(post.getBody());
            isChanged = true;
        }
        if (isChanged) {
            try {
                HttpResponse<String> response = GetData.INSTANCE.patchData(oldPost, POSTS_URL + "/" + id);
                oldPost = gson.fromJson(response.body(), PostFull.class);
                log.addLog(LocalDateTime.now(), POSTS_URL, CrudOperations.PATCH.name()  + "/" + id, "SUCCESS");
            } catch (IOException | InterruptedException e) {
                log.addLog(LocalDateTime.now(), POSTS_URL, CrudOperations.PATCH.name()  + "/" + id, "FAIL");
                throw new RuntimeException(e);
            }
        }else {
            log.addLog(LocalDateTime.now(), POSTS_URL, CrudOperations.PATCH.name()  + "/" + id, "FAIL: Обновление не выполнено. Новые данные соответствуют старым.");
        }
        return oldPost;
    }

    @Override
    public void deletePost(Long id) {
        GetData.INSTANCE.deleteData(POSTS_URL + "/" + id);
        log.addLog(LocalDateTime.now(), POSTS_URL, CrudOperations.DELETE.name()  + "/" + id, "FAIL");

    }
}
