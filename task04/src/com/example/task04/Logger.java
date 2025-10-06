package com.example.task04;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Logger {
    private final static HashMap<String, Logger> loggers = new HashMap<>();

    private ArrayList<MessageHandler> handlers = new ArrayList<>();
    private final String name;

    private Level level;
    public Logger(String name) {
        this.name = name;
        level = Level.INFO;
        loggers.put(name, this);
        handlers.add(new ConsoleHandler());
    }
    public Logger(String name, Level type) {
        this.name = name;
        this.level = type;
        loggers.put(name, this);
        handlers.add(new ConsoleHandler());
    }

    public Logger(String name, ArrayList<MessageHandler> handlers) {
        this.name = name;
        level = Level.INFO;
        loggers.put(name, this);
        this.handlers = handlers;
    }
    public static Logger getLogger(String name) {
        if (loggers.get(name) == null) loggers.put(name, new Logger(name));
        return loggers.get(name);
    }
    public String getName() {
        return name;
    }
    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public void log(Level level, String message) {
        if (level.ordinal() >= this.level.ordinal()) {
            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd hh:mm:ss"));
            String printMessage = MessageFormat.format("[{0}] {1} {2} - {3}", level, date, name, message);
            for (MessageHandler handler: handlers) {
                handler.handle(printMessage);
            }
        }
    }

    public void log(Level level, String format, Object... objects) {
        if (this.level.ordinal() <= level.ordinal()) {
            String message = MessageFormat.format(format, objects);
            for (MessageHandler handler: handlers) {
                handler.handle(message);
            }
        };
    }

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void debug(String format, Object... objects) {
        log(Level.DEBUG, format, objects);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void info(String format, Object... objects) {
        log(Level.INFO, format, objects);
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void warning(String format, Object... objects) {
        log(Level.WARNING, format, objects);
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }

    public void error(String format, Object... objects) {
        log(Level.ERROR, format, objects);
    }
}
