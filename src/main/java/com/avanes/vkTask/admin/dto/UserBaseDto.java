package com.avanes.vkTask.admin.dto;

import com.avanes.vkTask.constants.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserBaseDto {
    private String email;
    private Roles role;
}
