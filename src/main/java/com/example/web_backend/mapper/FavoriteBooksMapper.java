package com.example.web_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web_backend.entity.FavoriteBooks;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FavoriteBooksMapper extends BaseMapper<FavoriteBooks> {
    @Select("SELECT book_id FROM favorite_books WHERE uid = #{uid}")
    public List<Integer>  selectByUid(int uid);//通过用户id获取用户收藏的书籍
    @Select("SELECT uid FROM favorite_books WHERE book_id = #{bookId}")//通过书籍id获取收藏该书籍的用户
    public List<Integer>  selectByBookId(int bookId);
    @Delete("DELETE FROM favorite_books WHERE uid = #{uid} AND book_id = #{bookId}")
    public void deleteByUidAndBookId(int uid,int bookId);
}
