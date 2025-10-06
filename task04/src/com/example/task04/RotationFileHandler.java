package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {
    private final ChronoUnit interval;
    private LocalDateTime currentStart;
    private FileWriter writer;

    public RotationFileHandler(ChronoUnit interval) {
        this.interval = interval;
        currentStart = LocalDateTime.now().truncatedTo(interval);
        openNewFile();
    }

    private void openNewFile() {
        String fileName = currentStart + ".log";
        try {
            if (writer != null) writer.close();
            writer = new FileWriter(fileName, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handle(String message) {
        LocalDateTime now = LocalDateTime.now();

        if (now.isAfter(currentStart.plus(1, interval))) {
            currentStart = now.truncatedTo(interval);
            openNewFile();
        }

        try {
            writer.write(message + "\n");
            writer.flush();
        } catch (IOException err) {
            throw new RuntimeException(err);
        }
    }
}
