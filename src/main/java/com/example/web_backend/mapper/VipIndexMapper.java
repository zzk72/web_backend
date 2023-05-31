package com.example.web_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web_backend.entity.VipIndex;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface VipIndexMapper extends BaseMapper<VipIndex> {
    @Select("SELECT * FROM vip_index WHERE vip_class = #{vip_class}")
    public VipIndex selectByVipClass(int vip_class);
    //通过vip等级获取vip折扣
    @Select("SELECT class_discount FROM vip_index WHERE vip_class = #{vip_class}")
    public double selectDiscountByVipClass(int vip_class);
}
