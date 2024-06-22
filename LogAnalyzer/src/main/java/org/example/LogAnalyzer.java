package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class LogAnalyzer {
        private List<LogRecord> logData;

        // constructor
        public LogAnalyzer(String filePath) {
            logData = new ArrayList<>();
            // performing loading and preparing of data;
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String line;
                boolean isHeader = true;
                while ((line = br.readLine()) != null) {
                    if(isHeader) {
                        isHeader = false;
                        continue;
                    }
                    String[] values = line.split(",");
                    if (values.length < 3) continue; // Ensure line has enough data
                    LogType logType = LogType.valueOf(values[0].trim());
                    String timestampStr = values[1].trim();
                    String message = values[2].trim();
                    try {
                        LocalDateTime timestamp = LocalDateTime.parse(timestampStr, formatter);
                        LogRecord record = new LogRecord(logType, timestamp, message);
                        logData.add(record);
                    } catch (Exception e) {
                        System.err.println("Failed to parse timestamp: " + timestampStr);
                    }
                }
            } catch (Exception e) {
                System.out.println("Some Error");
            }
        }

        // function to fetch most recent record
        public LogRecord getMostRecentRecordByLogType(LogType logType) {
            if(logData.isEmpty()) return null;
            LogRecord resultRecord = logData.get(0);
            for(LogRecord r : logData) {
                if(r.getLogType() == logType && r.getTimestamp().isAfter(resultRecord.getTimestamp())) {
                    resultRecord = r;
                }
            }
            if(resultRecord.getLogType() != logType) {
                throw new Error("No record Found");
            }
            return resultRecord;
        }

    // function to fetch most recent record
    public LogRecord getLastErrorLog() {
        if(logData.isEmpty()) return null;
        LogRecord resultRecord = logData.get(0);
        for(LogRecord r : logData) {
            if(r.getLogType() == LogType.ERROR && r.getTimestamp().isAfter(resultRecord.getTimestamp())) {
                resultRecord = r;
            }
        }
        if(resultRecord.getLogType() != LogType.ERROR) {
            throw new Error("No record Found");
        }
        return resultRecord;
    }

    // function to fetch most recent record
    public List<LogRecord> getRecordsByMessage(String likeMessage) {
        if(logData.isEmpty()) return null;
        List<LogRecord> resultRecords = new ArrayList<>();
        for(LogRecord r : logData) {
            if(r.getMessage().contains(likeMessage)) {
                resultRecords.add(r);
            }
        }
        if(resultRecords.isEmpty()) {
            throw new Error("No record Found");
        }
        return resultRecords;
    }
}
