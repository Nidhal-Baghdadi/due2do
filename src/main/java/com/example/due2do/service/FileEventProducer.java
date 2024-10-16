package com.example.due2do.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FileEventProducer {

    private static final String TOPIC = "file-events";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendEvent(String message) {
        kafkaTemplate.send(TOPIC, message);
    }
}
