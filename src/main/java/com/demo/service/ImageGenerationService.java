package com.demo.service;

import com.demo.adapter.LocalStorageAdapter;
import com.demo.event.ImageGeneratedEvent;
import com.demo.factory.ImageProviderFactory;
import com.demo.model.ImageResponse;
import com.demo.strategy.ImageGenerationStrategy;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class ImageGenerationService {
    private final ImageProviderFactory factory;
    private final LocalStorageAdapter adapter;
    private final ApplicationEventPublisher eventPublisher;

    public ImageGenerationService(ImageProviderFactory factory, LocalStorageAdapter adapter, ApplicationEventPublisher eventPublisher) {
        this.factory = factory;
        this.adapter = adapter;
        this.eventPublisher = eventPublisher;
    }

    public ImageResponse processRequest(String prompt, String provider) {
        // 1. FACTORY: Get the correct AI tool
        ImageGenerationStrategy strategy = factory.getStrategy(provider);

        // 2. STRATEGY: Fetch the raw bytes from the AI
        byte[] rawBytes = strategy.generateImageBytes(prompt);

        // 3. ADAPTER: Save to disk and convert to URL format
        ImageResponse response = adapter.adaptAndSave(rawBytes, provider);

        // 4. OBSERVER: Tell the background thread to save to PostgreSQL
        eventPublisher.publishEvent(new ImageGeneratedEvent(prompt, response.getImageUrl(), provider));

        // 5. Return to user
        return response;
    }
}
