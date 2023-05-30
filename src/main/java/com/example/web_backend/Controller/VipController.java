package com.example.web_backend.Controller;

import com.example.web_backend.config.StateConstant;
import com.example.web_backend.entity.MessageEntity;
import com.example.web_backend.entity.User;
import com.example.web_backend.entity.Vip;
import com.example.web_backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VipController {
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/home/upgradeVip")
    public MessageEntity<String> vipUpdate(@RequestParam String username, @RequestParam String vip_class) {//返回当前等级

        User user = userMapper.selectByUsername(username);
        if (Integer.parseInt(user.getVipClass()) >= Integer.parseInt(vip_class)){
            return MessageEntity.error(StateConstant.BAD_REQUEST_CODE,StateConstant.VIP_CAN_NOT_DEGRADE_MSG);
        }
        userMapper.updateVip_class(username, vip_class);
        return MessageEntity.success(Vip.getVipDiscount(vip_class).second);
    }

    @GetMapping("/home/vipclass")
    public MessageEntity<String> vipClass(@RequestParam String username){
        User user = userMapper.selectByUsername(username);
        if(user==null) {
            return MessageEntity.error(StateConstant.USER_NOT_FOUND_CODE,StateConstant.USER_NOT_FOUND_MSG);
        }
        return MessageEntity.success(userMapper.selectByUsername(username).getVipClass());
    }
}
