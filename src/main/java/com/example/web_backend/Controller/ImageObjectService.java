package com.example.web_backend.Controller;

import com.example.web_backend.config.ImagePathConfig;
import lombok.Data;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Base64;

//图片资源服务类
@Data
public class ImageObjectService {
    private byte[] imageResource;//图片数据
    private String imageType;//图片类型
    private String retImagePath;//返回图片路径
    //传入图片路径，获取图片资源
    public ImageObjectService(String imagePath) throws IOException {
        retImagePath = imagePath;
        File imageFile = new File(retImagePath);
        //判断文件是否存在
        if(!imageFile.exists()){
            retImagePath = new ImagePathConfig().getDefaultImagePath();
            imageFile = new File(retImagePath);
        }
        //byte[] imageData = Files.readAllBytes(imageFile.toPath());
        //this.setImageResource(imageData);
        this.setImageType(CheckImageType(imageFile));
    }
    public ImageObjectService(File imageFile) throws IOException {
        byte[] imageData = Files.readAllBytes(imageFile.toPath());
        this.setImageResource(imageData);
        this.setImageType(CheckImageType(imageFile));
    }
    public static String CheckImageType(String imagePath) throws IOException {
        File imageFile = new File(imagePath);
        URLConnection connection = imageFile.toURL().openConnection();
        return connection.getContentType();
    }
    public static String CheckImageType(File imageFile) throws IOException {//检查图片类型
        URLConnection connection = imageFile.toURL().openConnection();
       return connection.getContentType();
    }
    //复制图片到指定路径
    public static void copyImage(String originPath,String targetPath) throws IOException {
        File originFile = new File(originPath);
        File targetFile = new File(targetPath);
        FileInputStream fis = new FileInputStream(originFile);
        byte[] imageBytes = IOUtils.toByteArray(fis);
        fis.close();
        Files.write(targetFile.toPath(),imageBytes);
    }
    public ImageObjectService(String imagePath, String fuction2) throws IOException {//备用
        File imageFile = new File(imagePath);
        FileInputStream fis = new FileInputStream(imageFile);
        byte[] imageBytes = IOUtils.toByteArray(fis);
        fis.close();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        this.setImageResource(base64Image.getBytes());
        this.setImageType(CheckImageType(imageFile));
    }
}
