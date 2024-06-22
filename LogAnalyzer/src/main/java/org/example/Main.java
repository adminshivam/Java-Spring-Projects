package org.example;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        LogAnalyzer analyzer = new LogAnalyzer("src/main/java/org/example/Logs.csv");

        try {
            LogRecord mostRecentRecord = analyzer.getMostRecentRecordByLogType(LogType.INFO);
            System.out.println("mostRecentRecord of type " + mostRecentRecord.getLogType() + " : " + mostRecentRecord.toString());
        } catch (Error e) {
            System.out.println(e.getMessage());
        }

        try {
            LogRecord lastErrorLogRecord = analyzer.getLastErrorLog();
            System.out.println("lastErrorLogRecord : " + lastErrorLogRecord.toString());
        } catch (Error e) {
            System.out.println(e.getMessage());
        }

        try {
            List<LogRecord> recordsByMessage  = analyzer.getRecordsByMessage("successfully");
            System.out.println("Records that contains message : ");
            for(LogRecord record : recordsByMessage) {
                System.out.println(record.toString());
            }
        } catch (Error e) {
            System.out.println(e.getMessage());
        }




    }
}