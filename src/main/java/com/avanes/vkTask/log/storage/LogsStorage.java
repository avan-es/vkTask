package com.avanes.vkTask.log.storage;

import com.avanes.vkTask.log.model.LogData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "dbLogsStorage")
public interface LogsStorage extends JpaRepository<LogData, Long> {
    List<LogData> findAllByEmail(String email);
}
