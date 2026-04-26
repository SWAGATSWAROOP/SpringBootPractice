package com.demo.strategy;


import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component("pollinationsStrategy")
public class PollinationsStrategy implements ImageGenerationStrategy {
    private final RestClient restClient;

    public PollinationsStrategy(){
        this.restClient = RestClient.create();
    }

    @Override
    public byte[] generateImageBytes(String prompt){
        // Pollinations free API: just pass the prompt in the URL
        String url = "https://image.pollinations.ai/prompt/" + prompt.replace(" ", "%20");
        return restClient.get().uri(url).retrieve().body(byte[].class);
    }

    @Override
    public String getProviderName() {
        return "POLLINATIONS";
    }
}
