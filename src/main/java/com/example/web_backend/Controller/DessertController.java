package com.example.web_backend.Controller;

import com.example.web_backend.config.StateConstant;
import com.example.web_backend.entity.*;
import com.example.web_backend.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Objects;

@RestController
public class DessertController {
    @Autowired
    private DessertMapper dessertMapper;
    @Autowired
    private DessertOrderMapper dessertOrderMapper;
    private final String SourcePath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("static/")).getPath();
    //private final String SourcePath = this.getClass().getClassLoader().getResource("static/").getPath();

    private final String dessertImagePath = SourcePath+"dessert_pic/";
    @GetMapping("/admin/getAllDessert")
    public MessageEntity<List<Dessert>> getAllDessert() throws IOException {
        List<Dessert> desserts = dessertMapper.selectList(null);
        for (Dessert dessert:desserts) {
            dessert.setImagePath(dessertImagePath+dessert.getImagePath());
            ImageObject imageObject = new ImageObject(dessert.getImagePath());
            //dessert.setImageResource(imageObject.getImageResource());
            dessert.setImageType(imageObject.getImageType());
        }
        return MessageEntity.success(desserts);
    }
    @GetMapping("/admin/getDessertByName")
    public MessageEntity<List<Dessert> > getDessertByName(@RequestParam String name) throws IOException {
        List<Dessert> desserts = dessertMapper.selectByName(name);
        for (Dessert dessert :desserts) {
            dessert.setImagePath(dessertImagePath+dessert.getImagePath());
            ImageObject imageObject = new ImageObject(dessert.getImagePath());
            //dessert.setImageResource(imageObject.getImageResource());
            dessert.setImageType(imageObject.getImageType());
        }
        return MessageEntity.success(desserts);
    }
    @GetMapping("/dessert/getImageByDessertId")//Been tested
    public MessageEntity<ImageObject> getDessertImage(@RequestParam int dessertId) throws IOException {
        Dessert dessert = dessertMapper.selectById(dessertId);
        ImageObject imageObject = new ImageObject(dessertImagePath+dessert.getImagePath());
        return MessageEntity.success(imageObject);
    }
//    @PostMapping("/admin/addNewDessert")
//    public MessageEntity<String> addNewDessert(@RequestBody Dessert dessert, @RequestParam("file") MultipartFile file) {
//        if (dessertMapper.selectByName(dessert.getName()) != null)
//            return MessageEntity.error(StateConstant.DESSERT_NOT_FOUND_CODE,StateConstant.DESSERT_NOT_FOUND_MSG);
//        if (file.isEmpty())
//            return MessageEntity.error(StateConstant.IMAGE_IS_NULL_CODE,StateConstant.IMAGE_IS_NULL_MSG);
//
//        String filePath = dessertImagePath + file.getOriginalFilename();
//        dessert.setImagePath(filePath);
//        try {
//            File destFile = new File(filePath);
//            file.transferTo(destFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        dessertMapper.insert(dessert);
//        return MessageEntity.success(StateConstant.SUCCESS_MSG);//"添加成功";
//    }
@PostMapping("/admin/addNewDessert")
public MessageEntity<String> addNewDessert(@RequestBody Dessert dessert) throws IOException {//been tested
    if (!dessertMapper.selectByName(dessert.getName()).isEmpty()){
        return MessageEntity.error(StateConstant.DESSERT_ALREADY_EXIST_CODE,StateConstant.DESSERT_ALREADY_EXIST_MSG);
    }
    String resultPath = dessertImagePath+ dessert.getName() + ".jpg";
    File result = new File(resultPath);
    FileInputStream input = new FileInputStream(dessert.getImagePath());
    FileOutputStream out = new FileOutputStream(result);
    byte[] buffer = new byte[100];//缓冲区
    int hasRead = 0;
    while ((hasRead = input.read(buffer)) > 0) {
        out.write(buffer, 0, hasRead);
    }
    System.out.println(result.getAbsolutePath());
    input.close();//关闭
    out.close();
    dessert.setImagePath(dessert.getName() + ".jpg");
    dessertMapper.insert(dessert);
    return MessageEntity.success(StateConstant.SUCCESS_MSG);//"添加成功";
}
    @PostMapping("/admin/addDessert")
    public MessageEntity<String> addDessert(@RequestParam int id, @RequestParam int nums) {
        Dessert dessert = dessertMapper.selectById(id);
        if (dessert == null)
            return MessageEntity.error(StateConstant.DESSERT_NOT_FOUND_CODE,StateConstant.DESSERT_NOT_FOUND_MSG);
        dessertMapper.updateStorage(dessert.getStorage() + nums, id);
        return MessageEntity.success(StateConstant.SUCCESS_MSG);//"添加成功";
    }

    @PostMapping("/admin/deleteDessert")
    public MessageEntity<String> deleteDessert(@RequestParam int id) {
        Dessert dessert = dessertMapper.selectById(id);
        if (dessert == null)
            return  MessageEntity.error(StateConstant.DESSERT_NOT_FOUND_CODE,StateConstant.DESSERT_NOT_FOUND_MSG);
        dessertMapper.deleteById(dessert.getId());
        return MessageEntity.success(StateConstant.SUCCESS_MSG);//"下架成功";
    }


}
