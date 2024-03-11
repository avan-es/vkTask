package com.avanes.vkTask.posts.controller;

import com.avanes.vkTask.posts.POJO.Post;
import com.avanes.vkTask.posts.POJO.PostFull;
import com.avanes.vkTask.posts.service.PostService;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("avan/posts")
@Validated
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_POSTS')")
    @ResponseStatus(HttpStatus.OK)
    public PostFull getPostById(
            @PositiveOrZero @PathVariable Long id) {
        return postService.getPost(id);
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_POSTS')")
    @ResponseStatus(HttpStatus.OK)
    public PostFull[] getPosts() {
        return postService.getPosts();
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_POSTS')")
    @ResponseStatus(HttpStatus.CREATED)
    public PostFull addPost(
            @RequestBody Post post) {
        return postService.addPost(post);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_POSTS')")
    @ResponseStatus(HttpStatus.OK)
    public PostFull patchAlbum(
            @RequestBody Post post,
            @PositiveOrZero @PathVariable Long id) {
        return postService.patchPost(post, id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_POSTS')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(
            @PositiveOrZero @PathVariable Long id) {
        postService.deletePost(id);
    }

}
