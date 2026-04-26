package com.demo.adapter;

import com.demo.model.ImageResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class LocalStorageAdapter{
    private final Path storageDirectory = Paths.get("uploads/images");

    public LocalStorageAdapter() throws IOException {
        Files.createDirectories(storageDirectory);
    }

    public ImageResponse adaptAndSave(byte[] imageBytes,String providerName){
        try {
            // 1. Create a formatter (Result example: 20260426_180408_123)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");
            // 2. Get the current time and format it into a string
            String timestamp = LocalDateTime.now().format(formatter);
            // 3. Construct the new filename
            String filename = providerName.toLowerCase() + "_" + timestamp + ".jpg";
            Path targetLocation = this.storageDirectory.resolve(filename);
            Files.write(targetLocation,imageBytes);

            //Url to see the image
            String localHostUrl = "http://localhost:8080/images/" + filename;

            return ImageResponse.builder()
                    .imageUrl(localHostUrl)
                    .provider(providerName)
                    .status("SUCCESS")
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("Failed to Save Image",e);
        }
    }
}