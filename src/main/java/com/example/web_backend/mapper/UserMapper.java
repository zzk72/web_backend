package com.example.web_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web_backend.entity.User;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE username = #{username}")
    public User selectByUsername(@Param("username") String username);
    //查询某段时间内充值vip的用户
    @Select("SELECT * FROM user WHERE vip_start_date BETWEEN #{startDate} AND #{endDate} ORDER BY vip_start_date ")
    public List<User> selectVipByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate);
    @Select("SELECT * FROM user WHERE id = #{id}")
    public User selectById(@Param("id") int id);

    @Update("UPDATE user SET password = #{password} WHERE username = #{username}")
    public void updatePassword(@Param("username") String username, @Param("password") String password);

    @Update("UPDATE user SET balance = #{balance} WHERE username = #{username}")
    public void updateBalance(@Param("username") String username, @Param("balance") double balance);

    @Update("UPDATE user SET username = #{username} WHERE id = #{id}")
    public void updateUsername(@Param("username") String username, @Param("id") int id);

    @Update("UPDATE user SET vip_class = #{vip_class} vip_start_date = #{vipStartDate} WHERE username = #{username}")
    public void updateVipClass(@Param("username") String username, @Param("vip_class") int vip_class, @Param("vipStartDate") String vipStartDate);

    @Delete("DELETE * from user WHERE id = #{id}")
    public void deleteById(@Param("id") int id);

    //查询某用户的 vip_discount ,vip_discount在vip_index表中,通过vip_class查询
    @Select("SELECT class_discount " +
            "FROM vip_index " +
            "WHERE vip_class = (" +
            "SELECT vip_class FROM user WHERE id = #{id}" +
            ")")
    public double selectVipDiscountByUid(int id);
}


