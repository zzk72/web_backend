package com.example.web_backend.Controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.web_backend.entity.*;
import com.example.web_backend.mapper.BookMapper;
import com.example.web_backend.mapper.DessertOrderMapper;
import com.example.web_backend.mapper.ReadRecordMapper;
import com.example.web_backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
//import java.lang.foreign.SegmentScope;
import java.nio.file.Files;
import java.util.*;

@RestController
public class ReadController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private ReadRecordMapper readRecordMapper;
    @Autowired
    private DessertOrderMapper dessertOrderMapper;

    @GetMapping("/read/allBook")
    public List<Book> allBook(@RequestParam int bookNums){
        List<Book> books = bookMapper.selectAll();
        for (Book book : books) {
            String imagePath = book.getImagePath();
            try {
                File imageFile = new File(imagePath);
                byte[] imageData = Files.readAllBytes(imageFile.toPath());
                book.setImageResource(imageData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return books;
    }

    @GetMapping("/read/recommend")
    public List<Book> recommendBook(@RequestParam int recommend_nums){
        List<Book> allBooks = bookMapper.selectAll();
        if (allBooks.size() <= recommend_nums) {
            return allBooks;
        }
        Random random = new Random();
        int startIndex = random.nextInt(allBooks.size() - recommend_nums + 1);
        int endIndex = startIndex + recommend_nums;
        List<Book> books = allBooks.subList(startIndex, endIndex);
        for (Book book : books) {
            String imagePath = book.getImagePath();
            try {
                File imageFile = new File(imagePath);
                byte[] imageData = Files.readAllBytes(imageFile.toPath());
                book.setImageResource(imageData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return books;
    }

    @PostMapping("/read/apply")
    public String readBook(@RequestParam String username,@RequestParam int bookId){
        User user = userMapper.selectByUsername(username);
        Book book = bookMapper.selectById(bookId);
        List<DessertOrder> dessertOrders = dessertOrderMapper.selectByUid(user.getId());
        int i=0;
        for(DessertOrder dessertOrder : dessertOrders){
            if(Objects.equals(dessertOrder.getBuyTime(), new Date().toString()))i=1;
        }
        if(i==0&& Objects.equals(user.getVipClass(), "0"))return "未购买甜品且非会员无法入内";
        if(book.getStorage()==0)return "当前书本无库存";
        for(i=1;i<=1000;i++){
            if(Seat.seat[i]==0)break;
        }
        if(i>1000)return "目前无空位，请您等候";
        bookMapper.updateStorage(book.getStorage()-1,bookId);
        ReadRecord readRecord = new ReadRecord();
        readRecord.setBookId(book.getId());
        readRecord.setUid(user.getId());
        readRecord.setDate(new Date().toString());
        readRecordMapper.insert(readRecord);
        Seat.seat[i]=1;
        Seat.bookId[i]=bookId;
        Seat.user[i]=username;
        return "您的座位在"+i+"号，祝您阅读愉快!";
    }

    @PostMapping("/read/end")
    public String endRead(@RequestParam int seatId){
        Seat.seat[seatId]=0;
        Book book = bookMapper.selectById(Seat.bookId[seatId]);
        bookMapper.updateStorage(book.getStorage()+1, Seat.bookId[seatId]);
        return "结束阅读，再见！";
    }

    @GetMapping("/read/findBook")
    public String findBook(@RequestParam int id){
        Book book = bookMapper.selectById(id);
        return book.getStorage()==0?"当前书本无库存":("您要找的书在"+book.getLocation());
    }

    @GetMapping("/read/myRead")
    public List<ReadRecord> myRead(@RequestParam String username){
        User user = userMapper.selectByUsername(username);
        Map<String,Object> condition = new HashMap<>();
        condition.put("uid",user.getId());
        return readRecordMapper.selectByMap(condition);
    }

//    @PostMapping("/read/addFavoriteBooks")
//    public String addFavoriteBooks(@RequestParam String username,@RequestParam int bookId){
//        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
//        if(user!=null){
//            String favor= user.getFavoriteBooks();
//            favor+=(bookId+"/");
//            user.setFavoriteBooks(favor);
//            userMapper.updateById(user);
//            return "添加成功";
//        }
//        return "用户不存在";
//    }
//
//    @GetMapping("/read/getFavoriteBooks")
//    public List<Book> getFavoriteBooks(@RequestParam String username){
//        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
//        String[] bookIds = user.getFavoriteBooks().split("/");
//        return bookMapper.selectList(new QueryWrapper<Book>().in("id", (Object) bookIds));
//    }
//
//    @PostMapping("/read/deleteFavoriteBooks")
//    public String deleteFavoriteBooks(@RequestParam String username, @RequestParam int _bookId){
//        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));
//        String[] bookIds = user.getFavoriteBooks().split("/");
//        List<String> remainingIds = new ArrayList<>();
//        String bookId = String.valueOf(_bookId);
//        for (String id : bookIds) {
//            if (!Objects.equals(id, bookId)) {
//                remainingIds.add(id);
//            }
//        }
//        String updatedFavoriteBooks = String.join("/", remainingIds);
//        user.setFavoriteBooks(updatedFavoriteBooks);
//        userMapper.updateById(user);
//        return "删除成功";
//    }
}
