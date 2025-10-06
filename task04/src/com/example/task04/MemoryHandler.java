package com.example.task04;

import java.util.ArrayList;

public class MemoryHandler implements MessageHandler {

    private final int maxMessages;
    private final ArrayList<MessageHandler> handlers;
    private final ArrayList<String> messages = new ArrayList<>();

    public MemoryHandler (int maxMessages, ArrayList<MessageHandler> handlers){
        this.maxMessages = maxMessages;
        this.handlers = handlers;
    }

    @Override
    public void handle(String message) {
        messages.add(message);

        if (messages.size() >= maxMessages) {
            for (MessageHandler handler : handlers) {
                for (String currentMessage : messages) {
                    handler.handle(currentMessage);
                }
            }
            messages.clear();
        }
    }
}