package com.demo.factory;

import com.demo.strategy.ImageGenerationStrategy;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ImageProviderFactory {
    private final Map<String, ImageGenerationStrategy> strategies;

    // Spring automatically injects a List of all classes that implement ImageGenerationStrategy
    public ImageProviderFactory(List<ImageGenerationStrategy> strategyList) {
        this.strategies = strategyList.stream()
                .collect(Collectors.toMap(ImageGenerationStrategy::getProviderName, strategy -> strategy));
    }

    public ImageGenerationStrategy getStrategy(String providerName){
        ImageGenerationStrategy strategy = strategies.get(providerName.toUpperCase());
        if (strategy == null) {
            throw new IllegalArgumentException("Unsupported AI Provider: " + providerName);
        }
        return strategy;
    }
}
