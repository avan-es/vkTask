package com.avanes.vkTask.posts.service;

import com.avanes.vkTask.posts.POJO.Post;
import com.avanes.vkTask.posts.POJO.PostFull;

public interface PostService {
    PostFull getPost(Long id);

    PostFull[] getPosts();

    PostFull addPost(Post post);

    PostFull patchPost(Post post, Long id);

    void deletePost(Long id);
}
