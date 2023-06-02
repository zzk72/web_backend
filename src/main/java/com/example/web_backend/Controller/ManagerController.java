package com.example.web_backend.Controller;

import com.example.web_backend.config.StateConstant;
import com.example.web_backend.entity.Manager;
import com.example.web_backend.entity.MessageEntity;
import com.example.web_backend.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class ManagerController {
    @Autowired
    private ManagerMapper managerMapper;
    @GetMapping("/manager/login")
    public MessageEntity<String> managerLogin(@RequestParam String name, @RequestParam String password){
        Manager manager = managerMapper.selectByName(name);
        if(manager==null)return MessageEntity.error(StateConstant.MANAGER_NOT_FOUND_CODE,StateConstant.MANAGER_NOT_FOUND_MSG);
        if(!Objects.equals(manager.getPassword(),password))
            return MessageEntity.error(StateConstant.PASSWORD_ERROR_CODE,StateConstant.PASSWORD_ERROR_MSG);
        return MessageEntity.success(StateConstant.SUCCESS_MSG);
    }
}
