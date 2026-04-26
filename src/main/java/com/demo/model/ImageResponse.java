package com.demo.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageResponse {
    private String imageUrl;
    private String provider;
    private String status;
}
