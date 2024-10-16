package com.example.due2do.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FileEventConsumer {

    @KafkaListener(topics = "file-events", groupId = "group_id")
    public void consumeEvent(String message) {
        System.out.println("Received event: " + message);

        // Here, you can call Google Drive or iCloud API to fetch the file.
        // Then parse and process it, and call OpenAI API if needed.

        // Example:
        // fetchFileFromDrive(message);
        // parseFile();
        // processWithOpenAI();
    }
}
