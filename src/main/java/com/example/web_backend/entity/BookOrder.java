package com.example.web_backend.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@Entity
@TableName("bookPurchase")
public class BookOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int uid;
    private int bookId;
    private int buyNums;
    private String buyTime;
    private double discount;
    private double totalPrice;
    private int ebookFlag;

}
