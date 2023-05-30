package com.example.web_backend.Controller;

import com.example.web_backend.entity.Book;
import com.example.web_backend.entity.Dessert;
import com.example.web_backend.mapper.BookMapper;
import com.example.web_backend.mapper.DessertMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ImageController {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private DessertMapper dessertMapper;


    @GetMapping("buyBook/getImage")
    public ResponseEntity<Map<String, String>> getBookImage(@RequestParam int bookId) throws IOException {
        Book book = bookMapper.selectById(bookId);
        File imageFile = new File(book.getImagePath());
        FileInputStream fis = new FileInputStream(imageFile);
        byte[] imageBytes = IOUtils.toByteArray(fis);
        fis.close();

        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        Map<String, String> response = new HashMap<>();
        response.put("bookImage", base64Image);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/buyDessert/getImage")
    public ResponseEntity<Map<String, String>> getDessertImage(@RequestParam int dessertId) throws IOException {
        Dessert dessert = dessertMapper.selectById(dessertId);
        File imageFile = new File(dessert.getImagePath());
        FileInputStream fis = new FileInputStream(imageFile);
        byte[] imageBytes = IOUtils.toByteArray(fis);
        fis.close();

        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        Map<String, String> response = new HashMap<>();
        response.put("dessertImage", base64Image);

        return ResponseEntity.ok(response);
    }
}
