package com.example.web_backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class AdminBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int AdminId;//员工id
    private double bonus;//奖金

    private String date;//日期
    private double totalAmount;//总计
    @TableField(exist = false)
    private String adminName;
    @TableField(exist = false)
    private double salary;//工资
}
