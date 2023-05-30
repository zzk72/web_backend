package com.example.web_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web_backend.entity.Book;
import com.example.web_backend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
    @Select("SELECT * FROM book WHERE name = #{name}")
    public List<Book> selectByName(@Param("name") String name);

    @Select("SELECT * FROM book")
    public List<Book> selectAll();

    @Update("UPDATE book SET storage = #{storage} WHERE id = #{id}")
    public void updateStorage(@Param("storage") int storage, @Param("id") int id);


}
