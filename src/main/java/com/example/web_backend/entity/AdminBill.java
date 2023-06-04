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
    private double salary;//工资

    @TableField(exist = false)
    private String adminName;
    @TableField(exist = false)
    private double totalAmount;//奖金+工资,存在数据库会导致数据不一致

    public double getTotalAmount(){
        return this.bonus+this.salary;
    }
}
