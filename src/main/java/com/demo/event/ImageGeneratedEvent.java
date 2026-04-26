package com.demo.event;

// A Java Record is the perfect, lightweight way to pass data between classes.
public record ImageGeneratedEvent(
        String prompt,
        String localUrl,
        String providerName
) {}