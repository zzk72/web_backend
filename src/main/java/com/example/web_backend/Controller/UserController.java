package com.example.web_backend.Controller;

import com.example.web_backend.config.ImagePathConfig;
import com.example.web_backend.config.StateConstant;
import com.example.web_backend.entity.*;
import com.example.web_backend.mapper.UserMapper;
import com.example.web_backend.mapper.VipIndexMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Objects;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VipIndexMapper vipIndexMapper;
    private final String userImagePath = new ImagePathConfig().userImagePath;
    @PostMapping("/home/upgradeVip")
    public MessageEntity<String> vipUpdate(@RequestParam String username, @RequestParam int vip_class) {//返回当前等级名称

        User user = userMapper.selectByUsername(username);
        if (user == null) {
            return MessageEntity.error(StateConstant.USER_NOT_FOUND_CODE, StateConstant.USER_NOT_FOUND_MSG);
        }
        if (user.getVipClass()>vip_class){
            return MessageEntity.error(StateConstant.BAD_REQUEST_CODE,StateConstant.VIP_CAN_NOT_DEGRADE_MSG);
        }
        userMapper.updateVipClass(username, vip_class,DateService.getTodayDate());
        return MessageEntity.success(vipIndexMapper.selectByVipClass(vip_class).getClassName());
    }

    @GetMapping("/home/getVipClassByUsername")
    public MessageEntity<Integer> getVipClassByUsername(@RequestParam String username){
        User user = userMapper.selectByUsername(username);
        if(user==null) {
            return MessageEntity.error(StateConstant.USER_NOT_FOUND_CODE,StateConstant.USER_NOT_FOUND_MSG);
        }
        return MessageEntity.success(userMapper.selectByUsername(username).getVipClass());
    }
    @PostMapping("/user/login")
    public MessageEntity<Integer> login(@RequestParam String username, @RequestParam String password) {
        User _user = userMapper.selectByUsername(username);
        if (_user == null) return MessageEntity.error(StateConstant.USER_NOT_FOUND_CODE, StateConstant.USER_NOT_FOUND_MSG);
        if (_user.getPassword().equals(password)) {
            return MessageEntity.success(_user.getId());
        } else {
            return MessageEntity.error(StateConstant.PASSWORD_ERROR_CODE, StateConstant.PASSWORD_ERROR_MSG);
        }
    }
    @PostMapping("/user/createUser")
    public MessageEntity<Integer> createUser(@RequestParam String username, @RequestParam String password) {//注册成功返回User
        if (userMapper.selectByUsername(username) != null)
            return MessageEntity.error(StateConstant.USER_ALREADY_EXIST_CODE, StateConstant.USER_ALREADY_EXIST_MSG);
        else {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setVipClass(0);
            userMapper.insert(user);
            return MessageEntity.success(user.getId());
        }
    }
    @GetMapping("user/getUserByName")//Been tested
    public MessageEntity<User> getUserByName(@RequestParam String username) throws IOException {//图片只返回路径
        User user = userMapper.selectByUsername(username);
        if(user==null)
            return MessageEntity.error(StateConstant.USER_NOT_FOUND_CODE,StateConstant.USER_NOT_FOUND_MSG);

        user.setImagePath(userImagePath+user.getImagePath());
        ImageObjectService imageObjectService =new ImageObjectService(user.getImagePath());
        user.setImageType(imageObjectService.getImageType());
       // user.setImageResource(imageObject.getImageResource());
        return MessageEntity.success(user);
    }
    @GetMapping("user/getUserById")//Been tested
    public MessageEntity<User> getUserById(@RequestParam int id) throws IOException {//图片只返回路径
        User user = userMapper.selectById(id);
        if(user==null)
            return MessageEntity.error(StateConstant.USER_NOT_FOUND_CODE,StateConstant.USER_NOT_FOUND_MSG);

        user.setImagePath(userImagePath+user.getImagePath());
        ImageObjectService imageObjectService =new ImageObjectService(user.getImagePath());
        user.setImageType(imageObjectService.getImageType());
        // user.setImageResource(imageObject.getImageResource());
        return MessageEntity.success(user);
    }
    @GetMapping("/user/getBalance")
    public MessageEntity<Double> getBalance(@RequestParam String username) {//返回用户余额
        User user = userMapper.selectByUsername(username);
        if(user==null)
            return MessageEntity.error(StateConstant.USER_NOT_FOUND_CODE,StateConstant.USER_NOT_FOUND_MSG);
        return MessageEntity.success(user.getBalance());
    }

    @PostMapping("/user/chargeBalance")//been tested
    public MessageEntity<Double> chargeBalance(@RequestParam String username, @RequestParam double charge){// 返回新的余额
        User user = userMapper.selectByUsername(username);
        if(user==null)
            return MessageEntity.error(StateConstant.USER_NOT_FOUND_CODE,StateConstant.USER_NOT_FOUND_MSG);

        double newBalance = user.getBalance()+charge;
        userMapper.updateBalance(user.getUsername(),newBalance);

        return MessageEntity.success(newBalance);
    }

    @PostMapping("/user/changePassword")//been tested
    public MessageEntity<String> changePassword(@RequestParam String username, @RequestParam String former_password,
                                                @RequestParam String new_password) {
        User _user = userMapper.selectByUsername(username);
        if (_user == null) return MessageEntity.error(StateConstant.USER_NOT_FOUND_CODE, StateConstant.USER_NOT_FOUND_MSG);
        if (_user.getPassword().equals(former_password)) {
            userMapper.updatePassword(username, new_password);
            return MessageEntity.success(StateConstant.SUCCESS_MSG);
        } else {
            return MessageEntity.error(StateConstant.PASSWORD_ERROR_CODE, StateConstant.PASSWORD_ERROR_MSG);
        }
    }
    @PostMapping("/user/changeUsername")//been tested
    public MessageEntity<String> changeInformation(@RequestParam String former_username, @RequestParam String new_username,
                                                   @RequestParam String password){
        User user=userMapper.selectByUsername(former_username);
        if(user==null)return MessageEntity.error(StateConstant.USER_NOT_FOUND_CODE, StateConstant.USER_NOT_FOUND_MSG);
        if(!Objects.equals(password, user.getPassword()))
            return MessageEntity.error(StateConstant.PASSWORD_ERROR_CODE, StateConstant.PASSWORD_ERROR_MSG);
        userMapper.updateUsername(new_username,user.getId());
        return MessageEntity.success(StateConstant.SUCCESS_MSG);
    }



    @PostMapping("/home/deleteUser")
    public MessageEntity<String> deleteUser(@RequestParam String username){
        userMapper.deleteById(userMapper.selectByUsername(username).getId());
        return MessageEntity.success(StateConstant.SUCCESS_MSG);
    }
}
