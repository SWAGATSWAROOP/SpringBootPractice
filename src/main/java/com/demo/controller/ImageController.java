package com.demo.controller;

import com.demo.model.ImageResponse;
import com.demo.service.ImageGenerationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private final ImageGenerationService service;

    public ImageController(ImageGenerationService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    public ImageResponse generate(@RequestParam String prompt,@RequestParam(defaultValue = "POLLINATIONS") String provider) {
        return service.processRequest(prompt, provider);
    }
}