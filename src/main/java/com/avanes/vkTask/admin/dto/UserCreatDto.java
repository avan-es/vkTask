package com.avanes.vkTask.admin.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class UserCreatDto extends UserBaseDto {
    private String password;
}
