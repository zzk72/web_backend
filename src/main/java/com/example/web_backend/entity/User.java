package com.example.web_backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private double balance;
    private String imagePath;//头像路径
    private int vipClass;//vip等级
    private String briefIntroduction;
    private String vipStartDate;//vip开始时间
    @TableField(exist = false)
    private String imageType;//图片类型
    @TableField(exist = false)
    private byte[] imageResource;
}
