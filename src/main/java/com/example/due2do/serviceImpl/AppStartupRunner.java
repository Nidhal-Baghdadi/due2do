package com.example.due2do.serviceImpl;

import com.example.due2do.service.GoogleDriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AppStartupRunner implements CommandLineRunner {

    @Autowired
    private GoogleDriveService googleDriveService;

    @Override
    public void run(String... args) throws Exception {
        try {

            String pageToken = googleDriveService.getInitialPageToken();

            googleDriveService.watchDriveChanges(pageToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
