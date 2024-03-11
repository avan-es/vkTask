package com.avanes.vkTask.posts.service;

import com.avanes.vkTask.posts.POJO.Post;
import com.avanes.vkTask.posts.POJO.PostFull;
import com.avanes.vkTask.utils.GetData;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpResponse;

import static com.avanes.vkTask.constants.CrudOperations.GET;
import static com.avanes.vkTask.constants.TaskConstants.POSTS_URL;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    @Override
    @Cacheable(cacheNames = "postsById")
    public PostFull getPost(Long id) {
        StringBuilder builder = GetData.INSTANCE.getDataFromServer(POSTS_URL + "/" + id, GET);
        Gson g = new Gson();
        return g.fromJson(builder.toString(), PostFull.class);
    }

    @Override
    public PostFull[] getPosts() {
        StringBuilder builder = GetData.INSTANCE.getDataFromServer(POSTS_URL, GET);
        Gson g = new Gson();
        return g.fromJson(builder.toString(), PostFull[].class);
    }

    @Override
    public PostFull addPost(Post post) {
        PostFull result = new PostFull();
        Gson gson = new Gson();
        try {
            HttpResponse<String> response = GetData.INSTANCE.addData(post, POSTS_URL);
            result = gson.fromJson(response.body(), PostFull.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
