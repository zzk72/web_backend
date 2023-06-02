package com.example.web_backend.Controller;

import com.example.web_backend.config.StateConstant;
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

    @GetMapping("/book/getRecommend")
    public MessageEntity<List<Book>> recommendBook(@RequestParam int recommend_nums){
        List<Book> allBooks = bookMapper.selectAll();
        if (allBooks.size() <= recommend_nums) {
            return MessageEntity.success(allBooks);
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
        return MessageEntity.success(books);
    }

    @PostMapping("/read/applySeat")
    public MessageEntity<Integer> readBook(@RequestParam String username,@RequestParam int bookId){//return seatId
        User user = userMapper.selectByUsername(username);
        Book book = bookMapper.selectById(bookId);
        if(user == null)return MessageEntity.error(StateConstant.USER_NOT_FOUND_CODE,StateConstant.USER_NOT_FOUND_MSG);
        if(book == null)return MessageEntity.error(StateConstant.BOOK_NOT_FOUND_CODE,StateConstant.BOOK_NOT_FOUND_MSG);
        List<DessertOrder> dessertOrders = dessertOrderMapper.selectByDateAndUid(user.getId(),new Date().toString());

        if(dessertOrders.isEmpty()&& (user.getVipClass()==0))
            return MessageEntity.error(StateConstant.NOT_BUY_DESSERT_AND_NOT_VIP_CODE,StateConstant.NOT_BUY_DESSERT_AND_NOT_VIP_MSG);
        if(book.getStorage()==0)return MessageEntity.error(StateConstant.BOOK_NOT_FOUND_CODE,StateConstant.BOOK_NOT_FOUND_MSG);
        int freeSeat=-1;
        for(int i:Seat.seat){
            if (i == 0) {
                freeSeat = i;
                break;
            }
        }
        if(freeSeat<0)return MessageEntity.error(StateConstant.NO_SEAT_CODE,StateConstant.NO_SEAT_MSG);
        bookMapper.updateStorage(book.getStorage()-1,bookId);
        ReadRecord readRecord = new ReadRecord();
        readRecord.setBookId(book.getId());
        readRecord.setUid(user.getId());
        readRecordMapper.insert(readRecord);
        Seat.seat[freeSeat]=1;
        Seat.bookId[freeSeat]=bookId;
        Seat.user[freeSeat]=username;
        return MessageEntity.success(freeSeat);
    }

    @PostMapping("/read/end")
    public MessageEntity<String> endRead(@RequestParam int seatId){
        Seat.seat[seatId]=0;
        Book book = bookMapper.selectById(Seat.bookId[seatId]);
        bookMapper.updateStorage(book.getStorage()+1, Seat.bookId[seatId]);
        return MessageEntity.success(StateConstant.SUCCESS_MSG);
    }


    @GetMapping("/readRecord/getReadRecordByUsername")
    public MessageEntity<List<ReadRecord> > getReadRecordByUsername(@RequestParam String username){
        User user = userMapper.selectByUsername(username);
        if (user == null) return MessageEntity.error(StateConstant.USER_NOT_FOUND_CODE, StateConstant.USER_NOT_FOUND_MSG);
        List<ReadRecord> readRecords=readRecordMapper.selectByUid(user.getId());
        return MessageEntity.success(readRecords);
    }
    @GetMapping("/readRecord/getReadRecordByUid")
    public MessageEntity<List<ReadRecord> > getReadRecordByUid(@RequestParam int uid){
        List<ReadRecord> readRecords=readRecordMapper.selectByUid(uid);
        return MessageEntity.success(readRecords);
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
