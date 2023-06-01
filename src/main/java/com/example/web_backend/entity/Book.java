package com.example.web_backend.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String author;
    private double price;
    private String location;
    private String briefIntroduction;
    private String classification;
    private int storage;
    private double ePrice;
    private  String imagePath;
    @TableField(exist = false)
    private byte[] imageResource;
    @TableField(exist = false)
    private String imageType;

}
