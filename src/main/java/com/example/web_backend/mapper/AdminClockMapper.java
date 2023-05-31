package com.example.web_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web_backend.entity.AdminClock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminClockMapper extends BaseMapper<AdminClock> {
    //查询某天的所有打卡记录
    @Select("select * from admin_clock where date = #{date}")
    List<AdminClock> selectByDate(String date);
    //通过admin_id查询某段时间内的打卡记录
    @Select("select * from admin_clock where admin_id = #{adminId} and date >= #{startDate} and date <= #{endDate}")
    List<AdminClock> selectByIdAndDateRange(@Param("adminId") int adminId ,@Param("startDate") String startDate,@Param("endDate") String endDate);
}
