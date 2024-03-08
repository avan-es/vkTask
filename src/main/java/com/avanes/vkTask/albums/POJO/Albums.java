package com.avanes.vkTask.albums.POJO;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Albums {
    private Integer userId;
    private Integer id;
    private String title;
}
