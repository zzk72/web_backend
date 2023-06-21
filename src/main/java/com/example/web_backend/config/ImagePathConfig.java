package com.example.web_backend.config;

import lombok.Data;

import java.util.Objects;

@Data
public class ImagePathConfig {
    //获取项目taget目录下的static文件夹的路径
    public  String  SourcePath ;
    public  String userImagePath ;
    public String bookImagePath ;
    public String dessertImagePath;
    public  String defaultImagePath ;

    public ImagePathConfig(){
        SourcePath=Objects.requireNonNull(this.getClass().getClassLoader().getResource("static/")).getPath().substring(1);
        userImagePath = SourcePath+"user_pic/";
        bookImagePath = SourcePath+"book_pic/";
        dessertImagePath = SourcePath+"dessert_pic/";
        defaultImagePath = SourcePath+"default.jpg";
    }

}
