package edu.upenn.cit5940.logging;


import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Logger {

    private static Logger instance;
    private String logFileName;
    private Writer logFileWriter;

    private Logger(String logFilename) {}

    public static  Logger getInstance(String logFilename) {
        if (instance == null) {
            instance = new Logger(logFilename);
        }
        return instance;
    }

    public void log(String message) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String logMessage = timestamp + " " + message + "\n";
        try {
            if (logFileName != null) {
                if (logFileWriter == null) {
                    logFileWriter = new FileWriter(logFileName, true);
                }
                logFileWriter.write(logMessage);
                logFileWriter.flush();
            } else {
                System.err.print(logMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void setOutput(String fileName) {
        if (fileName == null) {
            logFileName = null;
            if (logFileWriter != null) {
                try {
                    logFileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                logFileWriter = null;
            }
        } else {
            if (!fileName.equals(logFileName)) {
                if (logFileWriter != null) {
                    try {
                        logFileWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    logFileWriter = new FileWriter(fileName, true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                logFileName = fileName;
            }
        }
    }
}