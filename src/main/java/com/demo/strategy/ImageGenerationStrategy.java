package com.demo.strategy;

public interface ImageGenerationStrategy {
    // Every AI provider must implement this method and return raw bytes
    byte[] generateImageBytes(String prompt);
    String getProviderName();
}