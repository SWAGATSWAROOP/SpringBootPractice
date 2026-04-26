package com.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

@Entity
@Table(name = "image_metadata")
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class ImageMetadata {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String prompt;
    private String localUrl;
    private String providerName;
    private LocalDateTime createdAt;
}