package com.example.web_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web_backend.entity.ReadRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReadRecordMapper extends BaseMapper<ReadRecord> {
    //通过uid获取阅读记录
    @Select("SELECT * FROM read_record WHERE uid = #{uid}")
    public List<ReadRecord> selectByUid(@Param("uid") int uid);
    //获取某本书的阅读记录
    @Select("SELECT * FROM read_record WHERE book_id = #{bookId}")
    public List<ReadRecord> selectByBookId(@Param("bookId") int bookId);
    //获取某个vip等级的用户的阅读记录
    @Select("SELECT * FROM read_record WHERE uid IN (SELECT id FROM user WHERE vip_class = #{vipClass})")
    public List<ReadRecord> selectByVipClass(@Param("vipClass") int vipClass);
    //获取某段时间内的阅读记录
    @Select("SELECT * FROM read_record WHERE read_date BETWEEN #{start} AND #{end}")
    public List<ReadRecord> selectByDateRange(@Param("start") String start, @Param("end") String end);
    //获取某个用户在某段时间内的阅读记录
    @Select("SELECT * FROM read_record WHERE uid = #{uid} AND read_date BETWEEN #{start} AND #{end}")
    public List<ReadRecord> selectByUidAndDateRange(@Param("uid") int uid, @Param("start") String start, @Param("end") String end);
}
