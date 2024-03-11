package com.avanes.vkTask.log.dto;

import com.avanes.vkTask.constants.DateConstants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogDataDto {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateConstants.DATE_PATTERN)
    private LocalDateTime lodTime;

    private String email;

    private String roles;

    private String url;

    private String params;

}
