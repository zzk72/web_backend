package com.example.web_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web_backend.entity.Dessert;
import com.example.web_backend.entity.DessertOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DessertOrderMapper extends BaseMapper<DessertOrder> {

    @Select("SELECT * FROM dessertPurchase WHERE uid = #{uid}")
    public List<DessertOrder> selectByUid(@Param("uid") int uid);
    //查询某个时间段内的所有订单
    @Select("SELECT * FROM dessertPurchase WHERE buy_time BETWEEN #{startDate} AND #{endDate}")
    public List<DessertOrder> selectByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);
    //通过甜品id获取订单
    @Select("SELECT * FROM dessertPurchase WHERE dessert_id = #{dessertId}")
    public List<DessertOrder> selectByDessertId(@Param("dessertId") int dessertId);
    //通过日期获取订单
    @Select("SELECT * FROM dessertPurchase WHERE buy_time = #{date}")
    public List<DessertOrder> selectByDate(@Param("date") String date);
    //获取某一段时间的给定用户的所有订单
    @Select("SELECT * FROM dessertPurchase WHERE uid = #{uid} AND buy_time BETWEEN #{startDate} AND #{endDate}")
    public List<DessertOrder> selectByDateRangeAndUid(@Param("uid") int uid, @Param("startDate") String startDate, @Param("endDate") String endDate);
}
