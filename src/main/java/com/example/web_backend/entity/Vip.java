package com.example.web_backend.entity;

import jdk.internal.net.http.common.Pair;

import java.util.HashMap;
import java.util.Map;

public class Vip {
    public static Map<String, Pair<Double,String>> vipMap  = new HashMap<String,Pair<Double,String>>() {{
        put("0",new Pair<Double,String>(1.00,"普通"));
        put("1",new Pair<Double,String>(0.90,"黄金"));
        put("2",new Pair<Double,String>(0.80,"白金"));
        put("3",new Pair<Double,String>(0.60,"钻石"));
    }};

    public static Map<String,Integer>vip_cost=new HashMap<String,Integer>(){{
        put("0",0);
        put("1",99);
        put("2",199);
        put("3",399);
    }};

    public static Pair<Double, String> getVipDiscount(String key) {
        // 如果键不存在，则返回 null 或其他默认值
        return vipMap.getOrDefault(key, null);
    }
}
