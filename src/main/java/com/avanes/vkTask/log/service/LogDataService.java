package com.avanes.vkTask.log.service;

import com.avanes.vkTask.log.model.LogData;

import java.time.LocalDateTime;

public interface LogDataService {
    LogData addLog(LocalDateTime dateTime, String url, String CRUD, String result);
}
