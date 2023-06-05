package com.example.web_backend.config;

import lombok.Data;

import java.util.Objects;

@Data
public class ImagePathConfig {
    //获取项目taget目录下的static文件夹的路径
    public  String  SourcePath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("static/")).getPath().substring(1);
    //获取项目resources目录下的static文件夹的路径
    //public  String  SourcePath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("static/")).getPath();
    public  String userImagePath = SourcePath+"user_pic/";
    public String bookImagePath = SourcePath+"book_pic/";
    public String dessertImagePath = SourcePath+"dessert_pic/";
}
