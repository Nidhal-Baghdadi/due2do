package com.example.due2do.controller;

import com.example.due2do.service.FileEventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhook")
public class WebhookController {

    @Autowired
    private FileEventProducer fileEventProducer;

    @PostMapping("/drive-upload")
    public ResponseEntity<?> handleDriveUpload(@RequestBody String payload) {

        String fileEventMessage = "New file uploaded in Google Drive: " + payload;
        System.out.println("fileEventMessage");
        System.out.println(fileEventMessage);

        fileEventProducer.sendEvent(fileEventMessage);

        return new ResponseEntity<>("Webhook received!",HttpStatus.OK);
    }

    @PostMapping("/icloud-upload")
    public ResponseEntity<?> handleIcloudUpload(@RequestBody String payload) {
        String fileEventMessage = "New file uploaded in iCloud: " + payload;
        fileEventProducer.sendEvent(fileEventMessage);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
