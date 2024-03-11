package com.avanes.vkTask.log.service;

import com.avanes.vkTask.log.model.LogData;
import com.avanes.vkTask.log.storage.LogsStorage;
import com.avanes.vkTask.config.security.GetAuthUserLogin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class LogDataServiceImpl implements LogDataService{

    private final LogsStorage storage;

    @Override
    public LogData addLog(LocalDateTime dateTime, String url, String crud, String result) {
        Map<Integer, String> authUserLogin = GetAuthUserLogin.INSTANCE.getAuthUserLogin();
        LogData logData = new LogData();
        logData.setLogTime(dateTime);
        logData.setEmail(authUserLogin.get(0));
        logData.setRoles(authUserLogin.get(1));
        logData.setUrl(url);
        logData.setParams(crud);
        logData.setResult(result);
        storage.save(logData);
        return logData;
    }

    @Override
    public List<LogData> getAllLogsFromBD() {
        return storage.getAllLogs();
    }
}