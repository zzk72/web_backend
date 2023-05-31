package com.example.web_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web_backend.entity.BookOrder;
import net.sf.json.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookOrderMapper extends BaseMapper<BookOrder> {
    //查询某天所有的订单
    @Select("SELECT * FROM book_order WHERE buy_time = #{date}")
    public List<BookOrder> selectByDate(@Param("date") String date);

    @Select("SELECT * FROM book_order WHERE uid = #{uid}")
    public List<BookOrder> selectByUid(@Param("uid") int uid);
    //通过书籍id查询订单
    @Select("SELECT * FROM book_order WHERE book_id = #{bid}")
    public List<BookOrder> selectByBid(@Param("bid") int bid);
    //查询某段时间内的订单
    @Select("SELECT * FROM book_order WHERE buy_time BETWEEN #{start} AND #{end}")
    public List<BookOrder> selectByDateRange(@Param("start") String start, @Param("end") String end);

    //查询某段时间内给定用户的订单
    @Select("SELECT * FROM book_order WHERE uid = #{uid} AND buy_time BETWEEN #{start} AND #{end}")
    public List<BookOrder> selectByDateRangeAndUid(@Param("uid") int uid, @Param("start") String start, @Param("end") String end);
    //在book,book_order,user表中查找某一段时间内的订单，返回username,book_name,buy_time,ebook_flag
//    @Select("SELECT username,book_name,buy_time,ebook_flag,buy_nums,total_price FROM book_order,user,book WHERE book_order.buy_time BETWEEN #{start} AND #{end} AND book_order.book_id = book.id AND book_order.uid = user.username")
//    public List<JSONObject> select

}

