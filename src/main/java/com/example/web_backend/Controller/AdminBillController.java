package com.example.web_backend.Controller;

import com.example.web_backend.entity.Admin;
import com.example.web_backend.entity.AdminBill;
import com.example.web_backend.entity.MessageEntity;
import com.example.web_backend.mapper.AdminBillMapper;
import com.example.web_backend.mapper.AdminMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminBillController {
    @Autowired
    private AdminBillMapper adminBillMapper;
    @Autowired
    private AdminMapper adminMapper;
    private double calculateAdminBillTotalAmount(List<AdminBill> adminBillList){
        double totalAmount=0;
        for(AdminBill adminBill:adminBillList){
            totalAmount+=adminBill.getBonus()+adminBill.getSalary();
        }
        return totalAmount;
    }
//    @GetMapping({"/admin/getAdminBillByDateRang"})
//    public MessageEntity<JSONObject> getAdminBillByDateRang(String startDate, String endDate){
//        List<AdminBill> adminBillList=adminBillMapper.selectByDateRange(startDate,endDate);
//        for(AdminBill adminBill:adminBillList){
//            Admin admin=adminMapper.selectById(adminBill.getAdminId());
//            adminBill.setAdminName(admin.getName());
//            adminBill.setTotalAmount(adminBill.getBonus()+adminBill.getSalary());
//        }
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put("totalAmount", calculateAdminBillTotalAmount(adminBillList));
//        jsonObject.put("adminBillList",adminBillList);
//        return MessageEntity.success(jsonObject);
//    }


}
