package com.example.web_backend.Controller;

import com.example.web_backend.config.StateConstant;
import com.example.web_backend.entity.*;
import com.example.web_backend.mapper.DessertMapper;
import com.example.web_backend.mapper.DessertOrderMapper;
import com.example.web_backend.mapper.UserMapper;
import jdk.internal.net.http.common.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DessertOrderController {
    @Autowired
    private DessertMapper dessertMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DessertOrderMapper dessertOrderMapper;
    @GetMapping("home/getDessertOrdersByUsername")//Been tested
    public MessageEntity<List<DessertOrder> > getDessertOrdersByUsername(@RequestParam String username){
        User user = userMapper.selectByUsername(username);
        if (user == null) // 用户不存在
            return MessageEntity.error(StateConstant.USER_NOT_FOUND_CODE,StateConstant.USER_NOT_FOUND_MSG);
        return MessageEntity.success(dessertOrderMapper.selectByUid(user.getId()));
    }
    @GetMapping("/admin/getDessertOrdersByUid")//Been tested
    public MessageEntity<List<DessertOrder> > getDessertOrdersByUid(@RequestParam int uid) {
        List<DessertOrder> dessertOrders=dessertOrderMapper.selectByUid(uid);
        return MessageEntity.success(dessertOrders);
    }
 /*
 * 使用selectByMap层次太深，会在Controller中涉及数据表，不建议使用
 * */
//    @GetMapping("/admin/getDessertOrdersByDessertId")
//    public MessageEntity<List<DessertOrder> > getDessertOrdersByDessertId(@RequestParam int dessertId) {
//        Map<String, Object> condition = new HashMap<>();
//        condition.put("dessert_id", dessertId);
//        List<DessertOrder> dessertOrders = dessertOrderMapper.selectByMap(condition);
//        return MessageEntity.success(dessertOrders);
//    }
    //通过甜品id获取订单
    @GetMapping("/admin/getDessertOrdersByDessertId")//Been tested
    public MessageEntity<List<DessertOrder> > getDessertOrdersByDessertId(@RequestParam int dessertId) {
        List<DessertOrder> dessertOrders = dessertOrderMapper.selectByDessertId(dessertId);
        return MessageEntity.success(dessertOrders);
    }
    //获取某一天的所有订单
    @GetMapping("/admin/getDessertOrdersByDate")//Been tested
    public MessageEntity<List<DessertOrder> > getDessertOrdersByDate(@RequestParam String date) {//获取某一天的所有订单
        List<DessertOrder> dessertOrders = dessertOrderMapper.selectByDate(date);
        return MessageEntity.success(dessertOrders);
    }
    //获取某一段时间的所有订单
    @GetMapping("/admin/getDessertOrdersByDateRange")//Been tested
    public MessageEntity<List<DessertOrder> > getDessertOrdersByDateRange(@RequestParam String startDate, @RequestParam String endDate) {
        if(startDate.compareTo(endDate) > 0){
            String temp = startDate;
            startDate = endDate;
            endDate = temp;
        }
        List<DessertOrder> dessertOrders = dessertOrderMapper.selectByDateRange(startDate, endDate);
        return MessageEntity.success(dessertOrders);
    }
    //获取某一段时间的给定用户的所有订单
    @GetMapping("/admin/getDessertOrdersByDateRangeAndUid")//Been tested
    public MessageEntity<List<DessertOrder> > getDessertOrdersByDateRangeAndUid(
            @RequestParam String startDate, @RequestParam String endDate, @RequestParam int uid) {
        if(startDate.compareTo(endDate) > 0){
            String temp = startDate;
            startDate = endDate;
            endDate = temp;
        }
        List<DessertOrder> dessertOrders = dessertOrderMapper.selectByDateRangeAndUid(uid,startDate, endDate);
        return MessageEntity.success(dessertOrders);
    }
    @PostMapping("/buyDessert")
    public MessageEntity<Double> buyDessert(@RequestParam String username, @RequestParam int dessertId, @RequestParam int nums) {
        Dessert dessert = dessertMapper.selectById(dessertId);
        User user = userMapper.selectByUsername(username);
        if (dessert == null)
            return MessageEntity.error(StateConstant.DESSERT_NOT_FOUND_CODE, StateConstant.DESSERT_NOT_FOUND_MSG);
        if (dessert.getStorage() < nums)
            return MessageEntity.error(StateConstant.DESSERT_NOT_ENOUGH_CODE, StateConstant.DESSERT_NOT_ENOUGH_MSG);
        if (user == null) return MessageEntity.error(StateConstant.USER_NOT_FOUND_CODE, StateConstant.USER_NOT_FOUND_MSG);

        String vip_class = user.getVipClass();

        Pair<Double, String> discount = Vip.getVipDiscount(vip_class);
        double totalPrice = dessert.getPrice() * discount.first * nums;

        if (user.getBalance() < totalPrice)
            return MessageEntity.error(StateConstant.USER_BALANCE_NOT_ENOUGH_CODE, StateConstant.USER_BALANCE_NOT_ENOUGH_MSG);

        userMapper.updateBalance(user.getUsername(), user.getBalance() - totalPrice);
        dessertMapper.updateStorage(dessert.getStorage() - nums, dessertId);

        DessertOrder dessertOrder = new DessertOrder();
        dessertOrder.setBuyTime((new Date()).toString());
        dessertOrder.setDiscount(discount.first);
        dessertOrder.setTotalPrice(totalPrice);
        dessertOrder.setDessertId(dessert.getId());
        dessertOrder.setUid(user.getId());
        dessertOrderMapper.insert(dessertOrder);

        return MessageEntity.success(totalPrice);
    }
}
