package com.example.web_backend.Controller;

import com.example.web_backend.config.StateConstant;
import com.example.web_backend.entity.*;
import com.example.web_backend.mapper.BookMapper;
import com.example.web_backend.mapper.BookOrderMapper;
import com.example.web_backend.mapper.UserMapper;
import com.example.web_backend.mapper.VipIndexMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookOrderController {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookOrderMapper bookOrderMapper;
    @Autowired
    private VipIndexMapper vipIndexMapper;
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

    @PostMapping("/buyBook")//Been tested
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

        int vip_class = user.getVipClass();

        double discount = vipIndexMapper.selectDiscountByVipClass(vip_class);
        double totalPrice=(ebook_flag==0?book.getPrice():book.getEPrice())*nums*discount;

        if (user.getBalance() < totalPrice) return MessageEntity.error(
                StateConstant.USER_BALANCE_NOT_ENOUGH_CODE, StateConstant.USER_BALANCE_NOT_ENOUGH_MSG);

        userMapper.updateBalance(user.getUsername(), user.getBalance() - totalPrice);
        if(ebook_flag==0)
            bookMapper.updateStorage(book.getStorage() - nums, bookId);

        BookOrder bookOrder = new BookOrder();
        bookOrder.setBookId(book.getId());
        bookOrder.setBuyNums(nums);
        bookOrder.setDiscount(discount);
        bookOrder.setTotalPrice(totalPrice);
        bookOrder.setEbookFlag(ebook_flag);
        bookOrder.setUid(user.getId());
        bookOrder.setBuyTime(DateService.getTodayDate());
        bookOrderMapper.insert(bookOrder);
        return MessageEntity.success(totalPrice);
    }
}
