package com.example.web_backend.config;

import java.util.Objects;

public class ImagePathConfig {
    String SourcePath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("static/")).getPath();
    public String userImagePath = SourcePath+"user_pic/";
    public String bookImagePath = SourcePath+"book_pic/";
    public String dessertImagePath = SourcePath+"dessert_pic/";
}
