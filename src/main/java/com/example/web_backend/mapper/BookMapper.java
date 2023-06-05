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
    @Select("SELECT * FROM book WHERE name = #{name} ")
    public List<Book> selectByName(@Param("name") String name);

    @Select("SELECT * FROM book")
    public List<Book> selectAll();

    @Update("UPDATE book SET storage = #{storage} WHERE id = #{id}")
    public void updateStorage(@Param("storage") int storage, @Param("id") int id);
    //获取书籍的数量
    @Select("SELECT COUNT(*) FROM book")
    public int getBookCount();
    //随机抽取5本书，如果书的数量不足5本，则抽取全部书籍
    @Select("SELECT * FROM book ORDER BY RAND() LIMIT #{num}")
    public List<Book> getRandomBooks(@Param("num") int num);


}
