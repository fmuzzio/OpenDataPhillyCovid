package edu.upenn.cit5940.logging;


import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Logger {

	protected static Logger instance;
	protected String logFileName;
	protected static Writer logFileWriter;
	private static String initialLogFileName;

    public Logger(String logFilename) {
    	this.logFileName = logFilename;
    }
    
    public static void setInitialLogFileName(String logFilename) {
        initialLogFileName = logFilename;
    }


    public synchronized static Logger getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Logger is not initialized. Call initialize(logFilename) before calling getInstance().");
        }
        return instance;
    }

    public static void initialize(String logFilename) {
        if (instance == null) {
            instance = new Logger(logFilename);
        } else {
            Logger.setInitialLogFileName(logFilename);
        }
    }


    public synchronized static void log(String message) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String logMessage = timestamp + " " + message + "\n";
        try {
            if (instance.logFileName != null) { 
                if (logFileWriter == null) {
                    //setting true so that data can be appended to existing logfile 
                    logFileWriter = new FileWriter(instance.logFileName, true);
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


    public synchronized void setOutput(String fileName) {
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