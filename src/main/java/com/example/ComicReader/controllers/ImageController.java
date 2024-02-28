package com.example.ComicReader.controllers;

import com.example.ComicReader.model.Image;
import com.example.ComicReader.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.file.Files;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepository imageRepository;
    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id) throws Exception {
        Image image = imageRepository.findById(id).orElse(null);
        assert image != null;
        String filePath = image.getFilePath();
        File file = new File(filePath);
        if (!file.exists()){
            throw new Exception("File not found " + filePath);
        }
        byte[] imageBytes = Files.readAllBytes(file.toPath());
        return ResponseEntity.ok().header("fileName", image.getFileName()).contentType(MediaType.valueOf(image
                .getContentType())).contentLength(image.getSize()).body(new InputStreamResource(new ByteArrayInputStream(imageBytes)));
    }
}
