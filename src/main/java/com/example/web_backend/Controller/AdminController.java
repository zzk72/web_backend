package com.example.web_backend.Controller;

import com.example.web_backend.config.StateConstant;
import com.example.web_backend.entity.Admin;
import com.example.web_backend.entity.MessageEntity;
import com.example.web_backend.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
public class AdminController {
    @Autowired
    private AdminMapper adminMapper;

    @GetMapping("/admin/login")
    private MessageEntity<Admin> adminLogin(@RequestParam String name, @RequestParam String password) {

        Admin admin = adminMapper.selectByName(name);
        if (admin == null) return MessageEntity.error(StateConstant.ADMIN_NOT_FOUND_CODE, StateConstant.ADMIN_NOT_FOUND_MSG);
        if (!Objects.equals(admin.getPassword(), password))
            return MessageEntity.error(StateConstant.PASSWORD_ERROR_CODE, StateConstant.PASSWORD_ERROR_MSG);
        return MessageEntity.success(admin);
    }


    @PostMapping("/admin/adminRegister")
    public MessageEntity<Admin> adminRegister(@RequestBody Admin admin){
        if(adminMapper.selectByName(admin.getName())!=null)
            return MessageEntity.error(StateConstant.ADMIN_ALREADY_EXIST_CODE,StateConstant.ADMIN_ALREADY_EXIST_MSG);
        adminMapper.insert(admin);
        return MessageEntity.success(admin);
    }

    @PostMapping("/admin/setJob")
    public MessageEntity<String> setJob(@RequestParam String adminName,@RequestParam String job){
        Admin admin = adminMapper.selectByName(adminName);
        if (admin != null) {
            admin.setJob(job);
            adminMapper.updateById(admin);
            return MessageEntity.success(StateConstant.SUCCESS_MSG);
        } else {
            return MessageEntity.error(StateConstant.ADMIN_NOT_FOUND_CODE,StateConstant.ADMIN_NOT_FOUND_MSG);
        }
    }
    @PostMapping("/admin/deleteAdmin")
    public MessageEntity<String> deleteAdmin(@RequestParam String adminName){
        Admin admin = adminMapper.selectByName(adminName);
        if (admin != null) {
            adminMapper.deleteById(admin.getId());
            return MessageEntity.success(StateConstant.SUCCESS_MSG);
        } else {
            return MessageEntity.error(StateConstant.ADMIN_ALREADY_EXIST_CODE,StateConstant.ADMIN_NOT_FOUND_MSG);
        }
    }
    @GetMapping("/admin/getAllAdmin")
    public MessageEntity<List<Admin>> getAllAdmin(){
        return MessageEntity.success(adminMapper.selectAll());
    }
}
