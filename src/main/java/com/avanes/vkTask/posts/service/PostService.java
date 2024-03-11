package com.avanes.vkTask.posts.service;

import com.avanes.vkTask.posts.POJO.Post;
import com.avanes.vkTask.posts.POJO.PostFull;

public interface PostService {
    PostFull getPost(Long id);

    PostFull[] getPosts();

    Post addPost(Post post);
}
