package com.example.web_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FavoriteBooks extends BaseMapper<FavoriteBooks> {
    @Select("SELECT * FROM favorite_books WHERE uid = #{uid}")
    public FavoriteBooks selectByUid(int uid);//通过用户id获取用户收藏的书籍
    @Select("SELECT * FROM favorite_books WHERE bookId = #{bookId}")//通过书籍id获取收藏该书籍的用户
    public FavoriteBooks selectByBookId(int bookId);

}
