package com.example.web_backend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@ToString
@Data
@Entity
public class DessertOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int dessertId;
    private String buyTime;
    private double discount;
    private double totalPrice;
    private int uid;
    private int buyNums;

}
