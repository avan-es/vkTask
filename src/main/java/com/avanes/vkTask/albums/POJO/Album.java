package com.avanes.vkTask.albums.POJO;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Album {
    private Long userId;
    private String title;
}
