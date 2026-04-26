package com.demo.repository;

import com.demo.model.ImageMetadata; // Importing the model from the other folder
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageMetadataRepository extends JpaRepository<ImageMetadata, Long> {}