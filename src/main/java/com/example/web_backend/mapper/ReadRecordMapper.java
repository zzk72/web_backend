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
}
