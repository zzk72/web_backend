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

import static java.lang.Math.min;

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
    public MessageEntity<String> exitRead(@RequestParam int seatId){
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

}
