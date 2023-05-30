package com.example.web_backend.entity;

import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
@Data
public class ImageObject {
    private byte[] imageResource;
    private String imageType;
    public ImageObject(String imagePath) throws IOException {
        File imageFile = new File(imagePath);
        byte[] imageData = Files.readAllBytes(imageFile.toPath());
        this.setImageResource(imageData);
        URLConnection connection = imageFile.toURL().openConnection();
        String mimeType = connection.getContentType();
        this.setImageType(mimeType);
    }
}
