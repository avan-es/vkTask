package com.avanes.vkTask.albums.POJO;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlbumsPost {
    private Integer userId;
    private String title;
}
