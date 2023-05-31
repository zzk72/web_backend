package com.example.web_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web_backend.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM user WHERE username = #{username}")
    public User selectByUsername(@Param("username") String username);

    @Select("SELECT * FROM user WHERE id = #{id}")
    public User selectById(@Param("id") int id);

    @Update("UPDATE user SET password = #{password} WHERE username = #{username}")
    public void updatePassword(@Param("username") String username, @Param("password") String password);

    @Update("UPDATE user SET balance = #{balance} WHERE username = #{username}")
    public void updateBalance(@Param("username") String username, @Param("balance") double balance);

    @Update("UPDATE user SET username = #{username} WHERE id = #{id}")
    public void updateUsername(@Param("username") String username, @Param("id") int id);

    @Update("UPDATE user SET vip_class = #{vip_class} WHERE username = #{username}")
    public void updateVip_class(@Param("username") String username, @Param("vip_class") int vip_class);

    @Delete("DELETE * from user WHERE id = #{id}")
    public void deleteById(@Param("id") int id);

    void updateFavoriteBooks(@Param("username") String username, @Param("favoriteBooks") List<Integer> favoriteBooks);
}


