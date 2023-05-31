package com.example.web_backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class AdminClock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int adminId;

    private int isClock; //0表示未打卡，1表示已打卡,2表示请假
    private String date;//打卡日期
}
