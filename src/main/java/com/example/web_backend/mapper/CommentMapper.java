package com.example.web_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web_backend.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("SELECT * FROM comment WHERE bookId = #{bookId}")
    public List<Comment> selectByBook(@Param("bookId") int bookId);

    @Select("SELECT * FROM comment WHERE uid = #{uid}")
    public List<Comment> selectByUid(@Param("uid") int uid);
}
