package com.example.task01;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Logger {
    private static HashMap<String, Logger> loggers = new HashMap<>();
    private final String name;

    private MessageLevel level;
    public Logger(String name) {
        this.name = name;
        level = MessageLevel.INFO;
        loggers.put(name, this);
    }
    public Logger(String name, MessageLevel type) {
        this.name = name;
        this.level = type;
        loggers.put(name, this);
    }

    public static Logger getLogger(String name) {
        if (loggers.get(name) == null) loggers.put(name, new Logger(name));
        return loggers.get(name);
    }
    public String getName() {
        return name;
    }
    public void setLevel(MessageLevel level) {
        this.level = level;
    }

    public MessageLevel getLevel() {
        return level;
    }

    public void log(MessageLevel level, String message) {
        if (level.ordinal() >= this.level.ordinal()) {
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm:ss"));
            String printMessage = MessageFormat.format("[{0}] {1} {2} - {3}", level, date, name, message);
            System.out.println(printMessage);
        }
    }

    public void log(MessageLevel level, String format, Object... objects) {
        if (this.level.ordinal() <= level.ordinal()) System.out.println(MessageFormat.format(format, objects));
    }

    public void debug(String message) {
        log(MessageLevel.DEBUG, message);
    }

    public void debug(String format, Object... objects) {
        log(MessageLevel.DEBUG, format, objects);
    }

    public void info(String message) {
        log(MessageLevel.INFO, message);
    }

    public void info(String format, Object... objects) {
        log(MessageLevel.INFO, format, objects);
    }

    public void warning(String message) {
        log(MessageLevel.WARNING, message);
    }

    public void warning(String format, Object... objects) {
        log(MessageLevel.WARNING, format, objects);
    }

    public void error(String message) {
        log(MessageLevel.ERROR, message);
    }

    public void error(String format, Object... objects) {
        log(MessageLevel.ERROR, format, objects);
    }
}
