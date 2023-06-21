package com.example.web_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web_backend.entity.Book;
import com.example.web_backend.entity.Dessert;
import com.example.web_backend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DessertMapper extends BaseMapper<Dessert> {
    @Select("SELECT * FROM dessert WHERE name = #{name}")
    public List<Dessert> selectByName(@Param("name") String name);

    @Update("UPDATE dessert SET storage = #{storage} WHERE id = #{id}")
    public void updateStorage(@Param("storage") int storage, @Param("id") int id);
    //通过dessertId查找
    @Select("SELECT * FROM dessert WHERE id = #{id}")
    public Dessert selectById(@Param("id") int id);

    //查询某个价格区间的甜品
    @Select("SELECT * FROM dessert WHERE price BETWEEN #{start} AND #{end}")
    public List<Dessert> selectByPriceRange(@Param("start") double start, @Param("end") double end);

}
