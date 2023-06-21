package com.example.web_backend.Controller;

import com.example.web_backend.entity.*;
import com.example.web_backend.mapper.*;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

class FinanceType {
    public static int BOOK = 1;
    public static int DESSERT = 2;
    public static int VIP = 3;
    public static int ADMIN = 4;
}
@RestController
public class FinanceController {
    @Autowired
    private AdminBillMapper adminBillMapper;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookOrderMapper bookOrderMapper;
    @Autowired
    private VipIndexMapper vipIndexMapper;
    @Autowired
    private DessertMapper dessertMapper;
    @Autowired
    private DessertOrderMapper dessertOrderMapper;
    private void reviseDate(String startDate, String endDate){
        if(startDate.compareTo(endDate)>0){
            String temp=startDate;
            startDate=endDate;
            endDate=temp;
        }
    }
    //获取甜点销售额
    private double getDessertOrderTotalAmount(List<DessertOrder> dessertOrders) {//计算总金额
        double totalAmount = 0;
        for (DessertOrder dessertOrder : dessertOrders) {
            totalAmount += dessertOrder.getTotalPrice();
        }
        return totalAmount;
    }
    private double getVipTotalAmount(List<User> userList){
        double totalAmount=0;
        for(User user:userList){
            totalAmount+=vipIndexMapper.selectCostByVipClass(user.getVipClass());
        }
        return totalAmount;
    }
    private double getBookOrderTotalAmount(List<BookOrder> bookOrders){
        Double totalAmount=0.0;
        for(BookOrder bookOrder:bookOrders){
            totalAmount+=bookOrder.getTotalPrice();
        }
        return totalAmount;
    }
    private double calculateAdminBillTotalAmount(List<AdminBill> adminBillList){
        double totalAmount=0;
        for(AdminBill adminBill:adminBillList){
            totalAmount-=adminBill.getTotalAmount();
        }
        return totalAmount;
    }
    //生成图书订单财务报表Item
    private JSONObject buildBookOrderJson(BookOrder bookOrder){
        JSONObject jsonObject=new JSONObject();
        Book book = bookMapper.selectById(bookOrder.getBookId());
        User user = userMapper.selectById(bookOrder.getUid());
        jsonObject.put("id", bookOrder.getId());
        jsonObject.put("bookName", book.getName());
        jsonObject.put("userName", user.getUsername());
        jsonObject.put("buyNums", bookOrder.getBuyNums());
        jsonObject.put("date", bookOrder.getBuyTime());
        jsonObject.put("ebookFlag", bookOrder.getEbookFlag());
        jsonObject.put("totalPrice", bookOrder.getTotalPrice());
        jsonObject.put("type", FinanceType.BOOK);
        return jsonObject;
    }
    //生成甜点订单财务报表Item
    public JSONObject buildDessertOrderJson(DessertOrder dessertOrder){
        JSONObject jsonObject=new JSONObject();
        Dessert dessert=dessertMapper.selectById(dessertOrder.getDessertId());
        User user=userMapper.selectById(dessertOrder.getUid());
        jsonObject.put("id",dessertOrder.getId());
        jsonObject.put("dessertName",dessert.getName());
        jsonObject.put("userName",user.getUsername());
        jsonObject.put("buyNums",dessertOrder.getBuyNums());
        jsonObject.put("date",dessertOrder.getBuyTime());
        jsonObject.put("totalPrice",dessertOrder.getTotalPrice());
        jsonObject.put("type",FinanceType.DESSERT);
        return jsonObject;
    }
    //生成会员订单财务报表Item
    public JSONObject buildVipOrderJson(User user){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("id",user.getId());
        jsonObject.put("userName",user.getUsername());
        jsonObject.put("date",user.getVipStartDate());
        jsonObject.put("vipClass",user.getVipClass());
        jsonObject.put("vipName",vipIndexMapper.selectNameByVipClass(user.getVipClass()));
        jsonObject.put("totalPrice",vipIndexMapper.selectCostByVipClass(user.getVipClass()));
        //随机生成int 购买数量
        jsonObject.put("buyNums",new Random().nextInt(10));
        jsonObject.put("type",FinanceType.VIP);
        return jsonObject;
    }
    //生成管理员财务报表Item
    public JSONObject buildAdminBillJson(AdminBill adminBill){
        JSONObject jsonObject=new JSONObject();
        Admin admin=adminMapper.selectById(adminBill.getAdminId());
        jsonObject.put("id",adminBill.getId());
        jsonObject.put("adminName",admin.getName());
        jsonObject.put("date",adminBill.getDate());
        jsonObject.put("totalAmount",adminBill.getTotalAmount());
        jsonObject.put("bonus",adminBill.getBonus());
        jsonObject.put("salary",adminBill.getSalary());
        jsonObject.put("type",FinanceType.ADMIN);
        return jsonObject;
    }
    //生成甜点订单财务报表
    public List<JSONObject> buildDessertOrderJsonList(List<DessertOrder> dessertOrders){
        List<JSONObject> jsonObjectList=new ArrayList<>();
        for(DessertOrder dessertOrder:dessertOrders){
            jsonObjectList.add(buildDessertOrderJson(dessertOrder));
        }
        return jsonObjectList;
    }
    //生成图书订单财务报表
    public List<JSONObject> buildBookOrderJsonList(List<BookOrder> bookOrders){
        List<JSONObject> jsonObjectList=new ArrayList<>();
        for(BookOrder bookOrder:bookOrders){
            jsonObjectList.add(buildBookOrderJson(bookOrder));
        }
        return jsonObjectList;
    }
    //生成员工账单财务报表
    public List<JSONObject> buildAdminBillJsonList(List<AdminBill> adminBills){
        List<JSONObject> jsonObjectList=new ArrayList<>();
        for(AdminBill adminBill:adminBills){
            jsonObjectList.add(buildAdminBillJson(adminBill));
        }
        return jsonObjectList;
    }
    //生成会员订单财务报表
    public List<JSONObject> buildVipOrderJsonList(List<User> userList){
        List<JSONObject> jsonObjectList=new ArrayList<>();
        for(User user:userList){
            jsonObjectList.add(buildVipOrderJson(user));
        }
        return jsonObjectList;
    }
    //获取某段时间员工账单
    @GetMapping({"/admin/getAdminBillByDateRang"})
    public MessageEntity<JSONObject> getAdminBillByDateRang(String startDate, String endDate){
        reviseDate(startDate,endDate);
        List<AdminBill> adminBillList=adminBillMapper.selectByDateRange(startDate,endDate);
        for(AdminBill adminBill:adminBillList){
            Admin admin=adminMapper.selectById(adminBill.getAdminId());
            adminBill.setAdminName(admin.getName());
            adminBill.setTotalAmount(adminBill.getBonus()+adminBill.getSalary());
        }
        JSONObject jsonObject=new JSONObject();
        double totalAmount=calculateAdminBillTotalAmount(adminBillList);
        totalAmount=Double.parseDouble(String.format("%.2f",totalAmount));
        jsonObject.put("totalAmount", totalAmount);
        jsonObject.put("adminBillList",adminBillList);
        return MessageEntity.success(jsonObject);
    }
    //获取某段时间内所有的图书订单流水
    @GetMapping("/admin/getBookOrdersByDateRange")//Been tested
    //查询某段时间内所有的图书订单流水
    public MessageEntity<JSONObject> getBookOrdersByDateRange(@RequestParam String startDate, @RequestParam String endDate) {
        //比较日期大小
        reviseDate(startDate,endDate);
        List<BookOrder> bookOrders=bookOrderMapper.selectByDateRange(startDate, endDate);
        double totalAmount= getBookOrderTotalAmount(bookOrders);
        JSONObject jsonObject=new JSONObject();
        List<JSONObject> jsonOrders=new java.util.ArrayList<JSONObject>();

        for(BookOrder bookOrder:bookOrders) {
            jsonOrders.add(buildBookOrderJson(bookOrder));
        }
        totalAmount=Double.parseDouble(String.format("%.2f",totalAmount));
        jsonObject.put("totalAmount",totalAmount);
        jsonObject.put("bookOrders",jsonOrders);
        return MessageEntity.success(jsonObject);
    }
    //查询某段时间内所有User充值vip的流水
    @GetMapping("/admin/getVipOrdersByDateRange")
    public MessageEntity<JSONObject> getVipOrdersByDateRange(@RequestParam String startDate,@RequestParam String endDate){
        reviseDate(startDate,endDate);
        List<User> users=userMapper.selectVipByDateRange(startDate,endDate);
        double totalAmount= getVipTotalAmount(users);
        JSONObject jsonObject=new JSONObject();
        List<JSONObject> jsonOrders=new java.util.ArrayList<JSONObject>();
        for(User user:users){
            jsonOrders.add(buildVipOrderJson(user));
        }
        totalAmount=Double.parseDouble(String.format("%.2f",totalAmount));
        jsonObject.put("totalAmount",totalAmount);
        jsonObject.put("vipOrders",jsonOrders);
        return MessageEntity.success(jsonObject);
    }
    //查询某段时间内所有的甜品订单流水
    @GetMapping("/admin/getDessertOrdersByDateRange")//Been tested
    public MessageEntity<JSONObject > getDessertOrdersByDateRange(@RequestParam String startDate, @RequestParam String endDate) {
        reviseDate(startDate,endDate);
        List<DessertOrder> dessertOrders = dessertOrderMapper.selectByDateRange(startDate, endDate);
        double totalAmount = getDessertOrderTotalAmount(dessertOrders);
        JSONObject jsonObject = new JSONObject();
        List<JSONObject> jsonOrders=new java.util.ArrayList<JSONObject>();

        for(DessertOrder dessertOrder:dessertOrders) {
            jsonOrders.add(buildDessertOrderJson(dessertOrder));
        }
        totalAmount=Double.parseDouble(String.format("%.2f",totalAmount));
        jsonObject.put("totalAmount",totalAmount);
        jsonObject.put("bookOrders",jsonOrders);
        return MessageEntity.success(jsonObject);
    }
    //查询某段时间内所有的订单流水
    @GetMapping("admin/getAllFinanceInfoByDateRange")
    public MessageEntity<JSONObject> getAllFinanceInfoByDateRange(@RequestParam String startDate,@RequestParam String endDate){
        reviseDate(startDate,endDate);
        List<BookOrder> bookOrders=bookOrderMapper.selectByDateRange(startDate,endDate);
        List<DessertOrder> dessertOrders=dessertOrderMapper.selectByDateRange(startDate,endDate);
        List<AdminBill> adminBills=adminBillMapper.selectByDateRange(startDate,endDate);
        List<User> users=userMapper.selectVipByDateRange(startDate,endDate);
        double totalAmount=calculateAdminBillTotalAmount(adminBills)+
                getBookOrderTotalAmount(bookOrders) +
                getDessertOrderTotalAmount(dessertOrders)+
                getVipTotalAmount(users);
        //将totalAmount保留两位小数
        totalAmount=Double.parseDouble(String.format("%.2f",totalAmount));
        //对所有订单进行排序
        List<JSONObject> jsonObjectList=new ArrayList<>();
        jsonObjectList.addAll(buildBookOrderJsonList(bookOrders));
        jsonObjectList.addAll(buildDessertOrderJsonList(dessertOrders));
        jsonObjectList.addAll(buildAdminBillJsonList(adminBills));
        jsonObjectList.addAll(buildVipOrderJsonList(users));
        jsonObjectList.sort(new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                return o1.getString("date").compareTo(o2.getString("date"));
            }
        });
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("totalAmount",totalAmount);
        jsonObject.put("financeInfoList",jsonObjectList);
        return MessageEntity.success(jsonObject);
    }
}
