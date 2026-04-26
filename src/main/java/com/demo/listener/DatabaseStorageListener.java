package com.demo.listener;


import com.demo.event.ImageGeneratedEvent;
import com.demo.model.ImageMetadata;
import com.demo.repository.ImageMetadataRepository;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DatabaseStorageListener {
    private final ImageMetadataRepository repository;

    public DatabaseStorageListener(ImageMetadataRepository repository){
        this.repository = repository;
    }

    @Async //runs on a parallel thread
    @EventListener
    public void handleImageGeneratedEvent(ImageGeneratedEvent event){
        ImageMetadata metadata = ImageMetadata.builder()
                .prompt(event.prompt())
                .localUrl(event.localUrl())
                .providerName(event.providerName())
                .createdAt(LocalDateTime.now())
                .build();
        repository.save(metadata);
    }
}