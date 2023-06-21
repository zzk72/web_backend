package com.example.web_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web_backend.entity.FavoriteBooks;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FavoriteBooksMapper extends BaseMapper<FavoriteBooks> {
    //通过用户id获取用户收藏的书籍
    @Select("SELECT DISTINCT book_id FROM favorite_books WHERE uid = #{uid}")
    public List<Integer>  selectByUid(int uid);//通过用户id获取用户收藏的书籍

    //通过书籍id获取收藏该书籍的用户id,并去重
    @Select("SELECT DISTINCT uid FROM favorite_books WHERE book_id = #{bookId}")
    public List<Integer>  selectByBookId(int bookId);
    @Delete("DELETE FROM favorite_books WHERE uid = #{uid} AND book_id = #{bookId}")
    public void deleteByUidAndBookId(@Param("uid") int uid,@Param("bookId") int bookId);
    @Select("SELECT * FROM favorite_books WHERE uid = #{uid} AND book_id = #{bookId}")
    public FavoriteBooks selectByUidAndBookId(@Param("uid") int uid,@Param("bookId") int bookId);

    //查看某个vip等级的用户收藏的书籍
    @Select("SELECT DISTINCT book_id FROM favorite_books WHERE uid IN (SELECT id FROM user WHERE vip_class = #{vipClass})")
    public List<Integer> selectByVipClass(@Param("vipClass") int vipClass);

}
