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

    @Select("SELECT * FROM dessert_order WHERE uid = #{uid} ORDER BY buy_time ")
    public List<DessertOrder> selectByUid(@Param("uid") int uid);
    //查询某个时间段内的所有订单
    @Select("SELECT * FROM dessert_order WHERE buy_time BETWEEN #{startDate} AND #{endDate} ORDER BY buy_time ")
    public List<DessertOrder> selectByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);
    //通过甜品id获取订单
    @Select("SELECT * FROM dessert_order WHERE dessert_id = #{dessertId} ORDER BY buy_time ")
    public List<DessertOrder> selectByDessertId(@Param("dessertId") int dessertId);
    //通过日期获取订单
    @Select("SELECT * FROM dessert_order WHERE buy_time = #{date}")
    public List<DessertOrder> selectByDate(@Param("date") String date);
    //根据用户id获取某天该用户所有订单
    @Select("SELECT * FROM dessert_order WHERE uid = #{uid} AND buy_time = #{date}")
    public List<DessertOrder> selectByDateAndUid(@Param("uid") int uid, @Param("date") String date);
    //获取某一段时间的给定用户的所有订单
    @Select("SELECT * FROM dessert_order WHERE uid = #{uid} AND buy_time BETWEEN #{startDate} AND #{endDate}")
    public List<DessertOrder> selectByDateRangeAndUid(@Param("uid") int uid,
                                                      @Param("startDate") String startDate,
                                                      @Param("endDate") String endDate);

    //获取某一段时间的某价格区间的所有订单
    @Select("SELECT * " +
            "FROM dessert_order " +
            "WHERE total_price " +
            "BETWEEN #{startPrice} AND #{endPrice} " +
            "AND buy_time BETWEEN #{startDate} AND #{endDate}")
    public List<DessertOrder> selectByDateRangeAndPriceRange(@Param("startPrice") int startPrice,
                                                             @Param("endPrice") int endPrice,
                                                             @Param("startDate") String startDate,
                                                             @Param("endDate") String endDate);
}
