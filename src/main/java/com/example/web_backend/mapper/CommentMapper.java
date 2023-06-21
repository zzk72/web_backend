package com.example.web_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web_backend.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("SELECT * FROM comment WHERE book_id = #{bookId}")
    public List<Comment> selectByBook(@Param("bookId") int bookId);

    @Select("SELECT * FROM comment WHERE uid = #{uid}")
    public List<Comment> selectByUid(@Param("uid") int uid);

    //查询某个等级vip的用户的评论
    @Select("SELECT * FROM comment WHERE uid IN (SELECT id FROM user WHERE vip_class = #{vipClass})")
    public List<Comment> selectByVipClass(@Param("vipClass") int vipClass);

    //查询某段时间内的评论
    @Select("SELECT * FROM comment WHERE comment_date BETWEEN #{start} AND #{end}")
    public List<Comment> selectByDateRange(@Param("start") String start, @Param("end") String end);
}
