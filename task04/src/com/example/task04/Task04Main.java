package com.example.task04;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Task04Main {
    public static void main(String[] args) {
        ArrayList<MessageHandler> handlers = new ArrayList<>();
        ArrayList<MessageHandler> handlers1 = new ArrayList<>();
        handlers.add(new ConsoleHandler());
        handlers.add(new FileHandler());
        handlers.add(new RotationFileHandler(ChronoUnit.MINUTES));
        handlers1.add(new MemoryHandler(2, handlers));
        Logger logger = new Logger("Logger1", handlers1);
        logger.log(Level.INFO, "test");
        logger.log(Level.WARNING, "test warning");
        logger.log(Level.INFO, "test");
        logger.log(Level.WARNING, "test warning");
    }
}
