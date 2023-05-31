package com.example.web_backend.Controller;

import com.example.web_backend.config.StateConstant;
import com.example.web_backend.entity.*;
import com.example.web_backend.mapper.BookMapper;
import com.example.web_backend.mapper.BookOrderMapper;
import com.example.web_backend.mapper.UserMapper;
import jdk.internal.net.http.common.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import net.sf.json.JSONObject;
import java.util.List;

@RestController
public class BookOrderController {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookOrderMapper bookOrderMapper;
    private double caculateTotalAmount(List<BookOrder> bookOrders){
    Double totalAmount=0.0;
    for(BookOrder bookOrder:bookOrders){
        totalAmount+=bookOrder.getTotalPrice();
    }
    return totalAmount;
}
    @GetMapping("home/getAllBookOrders")//Been tested
    public MessageEntity<List<BookOrder> > getAllBookOrders(@RequestParam String username){
        User user = userMapper.selectByUsername(username);
        if (user == null) // 用户不存在
            return MessageEntity.error(StateConstant.USER_NOT_FOUND_CODE,StateConstant.USER_NOT_FOUND_MSG);
        return MessageEntity.success(bookOrderMapper.selectByUid(user.getId()));
    }
    @GetMapping("/admin/getBookOrdersByBookId")//Been tested
    public MessageEntity<List<BookOrder> > getBookOrdersByBookId(@RequestParam int bookId) {
        return MessageEntity.success(bookOrderMapper.selectByBid(bookId));
    }
    @GetMapping("/admin/getBookOrdersByUserId")//Been tested
    public MessageEntity<List<BookOrder> > getBookOrdersByUserId(@RequestParam int UserId) {
        return MessageEntity.success(bookOrderMapper.selectByUid(UserId));
    }

    @GetMapping("/admin/getBookOrdersByDateRange")//Been tested
    //查询某段时间内所有的图书订单流水
    public MessageEntity<JSONObject> getBookOrdersByDateRange(@RequestParam String startDate, @RequestParam String endDate) {
        //比较日期大小
        if(startDate.compareTo(endDate)>0){
            String temp=startDate;
            startDate=endDate;
            endDate=temp;
        }
        List<BookOrder> bookOrders=bookOrderMapper.selectByDateRange(startDate, endDate);
        double totalAmount=caculateTotalAmount(bookOrders);
        JSONObject jsonObject=new JSONObject();
        List<JSONObject> jsonOrders=new java.util.ArrayList<JSONObject>();

        for(BookOrder bookOrder:bookOrders) {
            JSONObject jsonOrder = new JSONObject();
            Book book = bookMapper.selectById(bookOrder.getBookId());
            User user = userMapper.selectById(bookOrder.getUid());
            jsonOrder.put("id", bookOrder.getId());
            jsonOrder.put("bookName", book.getName());
            jsonOrder.put("userName", user.getUsername());
            jsonOrder.put("buyNums", bookOrder.getBuyNums());
            jsonOrder.put("buyTime", bookOrder.getBuyTime());
            jsonOrder.put("ebookFlag", bookOrder.getEbookFlag());
            jsonOrder.put("totalPrice", bookOrder.getTotalPrice());
            jsonOrders.add(jsonOrder);
        }
        jsonObject.put("totalAmount",totalAmount);
        jsonObject.put("bookOrders",jsonOrders);
        return MessageEntity.success(jsonObject);
    }

    @GetMapping("/admin/getBookOrdersByDateRangeAndUserId")//Been tested
    //查询某段时间内给定用户的订单及总额
    public MessageEntity<List<BookOrder> > getBookOrdersByDateRangeAndUserId(
            @RequestParam String startDate, @RequestParam String endDate, @RequestParam int userId) {
        //比较日期大小
        if(startDate.compareTo(endDate)>0){
            String temp=startDate;
            startDate=endDate;
            endDate=temp;
        }
        List<BookOrder> bookOrders=bookOrderMapper.selectByDateRangeAndUid( userId,startDate, endDate);
        double totalAmount=caculateTotalAmount(bookOrders);
        return MessageEntity.success(bookOrders);
    }

    @GetMapping("/admin/getBookOrdersByDate")
    //查询某天内所有的订单
    public MessageEntity<List<BookOrder> > getBookOrdersByDate(@RequestParam String date) {
        return MessageEntity.success(bookOrderMapper.selectByDate(date));
    }

    @PostMapping("/buyBook")
    //返回值为BaseEntity<Double>，其中Double为总花费
    public MessageEntity<Double> buyBook(@RequestParam String username, @RequestParam int bookId, @RequestParam int nums,
                                         @RequestParam int ebook_flag) {
        Book book = bookMapper.selectById(bookId);
        User user = userMapper.selectByUsername(username);

        if (user == null) // 用户不存在
            return MessageEntity.error(StateConstant.USER_NOT_FOUND_CODE,StateConstant.USER_NOT_FOUND_MSG);
        if (book == null)// 图书不存在
            return MessageEntity.error(StateConstant.BOOK_NOT_FOUND_CODE,StateConstant.BOOK_NOT_FOUND_MSG);
        if (ebook_flag == 0 && book.getStorage() < nums)// 实体书库存不足
            return MessageEntity.error(StateConstant.BOOK_NOT_ENOUGH_CODE,StateConstant.BOOK_NOT_ENOUGH_MSG);

        String vip_class = user.getVipClass();

        Pair<Double, String> discount = Vip.getVipDiscount(vip_class);
        double totalPrice=(ebook_flag==0?book.getPrice():book.getEPrice())*nums*discount.first;

        if (user.getBalance() < totalPrice) return MessageEntity.error(
                StateConstant.USER_BALANCE_NOT_ENOUGH_CODE, StateConstant.USER_BALANCE_NOT_ENOUGH_MSG);

        userMapper.updateBalance(user.getUsername(), user.getBalance() - totalPrice);
        if(ebook_flag==0)
            bookMapper.updateStorage(book.getStorage() - nums, bookId);

        BookOrder bookOrder = new BookOrder();
        bookOrder.setBookId(book.getId());
        bookOrder.setBuyNums(nums);
        bookOrder.setDiscount(discount.first);
        bookOrder.setTotalPrice(totalPrice);
        bookOrder.setEbookFlag(ebook_flag);
        bookOrderMapper.insert(bookOrder);

        return MessageEntity.success(totalPrice);
    }
}
