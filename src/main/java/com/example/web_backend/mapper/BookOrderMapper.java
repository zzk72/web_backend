package com.example.web_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web_backend.entity.BookOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookOrderMapper extends BaseMapper<BookOrder> {
    //查询某天所有的订单
    @Select("SELECT * FROM bookpurchase WHERE buy_time = #{date}")
    public List<BookOrder> selectByDate(@Param("date") String date);

    @Select("SELECT * FROM bookpurchase WHERE uid = #{uid}")
    public List<BookOrder> selectByUid(@Param("uid") int uid);
    //通过书籍id查询订单
    @Select("SELECT * FROM bookpurchase WHERE book_id = #{bid}")
    public List<BookOrder> selectByBid(@Param("bid") int bid);
    //查询某段时间内的订单
    @Select("SELECT * FROM bookpurchase WHERE buy_time BETWEEN #{start} AND #{end}")
    public List<BookOrder> selectByDateRange(@Param("start") String start, @Param("end") String end);
    //查询某段时间内给定用户的订单
    @Select("SELECT * FROM bookpurchase WHERE uid = #{uid} AND buy_time BETWEEN #{start} AND #{end}")
    public List<BookOrder> selectByDateRangeAndUid(@Param("uid") int uid, @Param("start") String start, @Param("end") String end);
}

