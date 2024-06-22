package org.example;

import java.time.LocalDateTime;

public class LogRecord {
    private LogType logType;
    private LocalDateTime timestamp;
    private String message;

    public LogRecord(LogType logType, LocalDateTime timestamp, String message) {
        this.logType = logType;
        this.timestamp = timestamp;
        this.message = message;
    }

    public LogType getLogType() {
        return logType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "LogRecord{" +
                "logType='" + logType + '\'' +
                ", timestamp=" + timestamp +
                ", message='" + message + '\'' +
                '}';
    }
}

