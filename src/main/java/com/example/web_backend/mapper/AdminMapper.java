package com.example.web_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web_backend.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    //通过名字查找
    @Select("SELECT * FROM admin WHERE name = #{name}")
    public Admin selectByName(String name);
}
