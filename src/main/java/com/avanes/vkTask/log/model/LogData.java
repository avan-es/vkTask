package com.avanes.vkTask.log.model;

import com.avanes.vkTask.constants.DateConstants;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
//@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Data
@Table(name = "logs")
public class LogData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = DateConstants.DATE_PATTERN)
    @Column(name = "date_time")
    private LocalDateTime logTime;

    @Column(name = "user_email")
    private String email;

    @Column(name = "u_role")
    private String roles;

    private String url;

    private String params;

    private String result;

    public LogData(LocalDateTime dateTime, String toString, String toString1, String url, String crud, String result) {

    }
}
