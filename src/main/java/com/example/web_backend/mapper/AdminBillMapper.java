package com.example.web_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web_backend.entity.AdminBill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminBillMapper extends BaseMapper<AdminBill> {
    @Select("select * from admin_bill where date >= #{startDate} and date <= #{endDate} ORDER BY date")
    List<AdminBill> selectByDateRange(@Param("startDate") String startDate,@Param("endDate") String endDate);
}
