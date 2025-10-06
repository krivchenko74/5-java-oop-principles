package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;

public class FileHandler implements MessageHandler {
    @Override
    public void handle(String message) {
        try {
            FileWriter writer = new FileWriter("logs.txt", true);

            writer.write(message);
            writer.append("\n");

            writer.flush();
        } catch(IOException err) {
            throw new RuntimeException(err);
        }
    }
}
