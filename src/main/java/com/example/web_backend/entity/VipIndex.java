package com.example.web_backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class VipIndex {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int vipClass;//vip等级
    private String className;//vip等级名称
    private double classDiscount;//折扣
    private double classCost;//费用
}
