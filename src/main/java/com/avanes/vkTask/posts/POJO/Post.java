package com.avanes.vkTask.posts.POJO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Post {

    private Long userId;
    private String title;
    private String body;
}
