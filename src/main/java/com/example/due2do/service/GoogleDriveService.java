package com.example.due2do.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.Channel;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Collections;

@Service
public class GoogleDriveService {
    private Drive driveService;

    public GoogleDriveService() throws IOException, GeneralSecurityException {

        GoogleCredentials credentials = ServiceAccountCredentials
                .fromStream(new ByteArrayInputStream(System.getenv("GOOGLE_CREDENTIALS_JSON").getBytes()))
                .createScoped(Collections.singleton(DriveScopes.DRIVE));

        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        driveService = new Drive.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                jsonFactory,
                new HttpCredentialsAdapter(credentials))
                .setApplicationName("personal app")
                .build();
    }

    public void watchDriveChanges(String pageToken) throws IOException {

        SecureRandom secureRandom = new SecureRandom();
        byte[] randomBytes = new byte[16]; // 16 bytes = 128 bits
        secureRandom.nextBytes(randomBytes);
        String channelId = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes); // Unique channel ID, ideally generated uniquely for each watch request
        String webhookUrl = "https://coyote-notable-neatly.ngrok-free.app/api/webhook/drive-upload";  // Update with your actual webhook URL

        Channel channel = new Channel();
        channel.setId(channelId);
        channel.setType("web_hook");
        channel.setAddress(webhookUrl);


        driveService.changes().watch(pageToken, channel).execute();
    }

    public String getInitialPageToken() throws IOException {

        return driveService.changes().getStartPageToken().execute().getStartPageToken();
    }


}
