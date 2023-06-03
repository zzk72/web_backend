package com.example.web_backend.config;

import lombok.Data;

@Data
public class StateConstant {
    // 错误码
    public static final int SUCCESS_CODE = 200;//成功
    public static final int BAD_REQUEST_CODE = 400;//错误请求
    public static final int REQUEST_NOT_FOUND_CODE = 404;//请求不存在
    public static final int REQUEST_METHOD_NOT_SUPPORTED_CODE = 405;//请求方法不支持
    public static final int SERVER_INTERNAL_ERROR_CODE = 500;//服务器内部错误
    public static final int PARAMS_NULL_CODE = 501;//参数为空
    public static final int PARAMS_ERROR_CODE = 502;//参数错误
    public static final int USER_NOT_FOUND_CODE = 503;//用户不存在
    public static final int PASSWORD_ERROR_CODE = 504;//密码错误
    public static final int USER_ALREADY_EXIST_CODE = 505;//用户已存在
    public static final int USER_BALANCE_NOT_ENOUGH_CODE = 506;//用户余额不足
    public static final int BOOK_NOT_FOUND_CODE=507;//图书不存在
    //图书已存在
    public static final int BOOK_ALREADY_EXIST_CODE=508;
    public static final int BOOK_NOT_ENOUGH_CODE=509;//图书库存不足
    public static final int DESSERT_NOT_FOUND_CODE=510;//甜点不存在
    public static final int DESSERT_NOT_ENOUGH_CODE=511; //甜点库存不足
    //图片为空
    public static final int IMAGE_IS_NULL_CODE=512;
    //员工已存在
    public static final int ADMIN_ALREADY_EXIST_CODE=513;
    //员工不存在
    public static final int ADMIN_NOT_FOUND_CODE=514;
    //经理不存在
    public static final int MANAGER_NOT_FOUND_CODE=515;
    //经理已存在
    public static final int MANAGER_ALREADY_EXIST_CODE=516;
    //未购买甜品且非会员无法入内NOT_BUY_DESSERT_AND_NOT_VIP
    public static final int NOT_BUY_DESSERT_AND_NOT_VIP_CODE=517;
    //没有空座位
    public static final int NO_SEAT_CODE=518;
    //甜点已存在
    public static final int DESSERT_ALREADY_EXIST_CODE=519;
    //收藏图书已存在
    public static final int FAVORITE_BOOK_ALREADY_EXIST_CODE=520;

    // 错误码对应的错误消息
    public static final String SUCCESS_MSG = "操作成功";
    public static final String SERVER_INTERNAL_ERROR_MSG = "The server you just visited has a internal error!";
    public static final String BAD_REQUEST_MSG = "Bad request!";
    public static final String REQUEST_NOT_FOUND_MSG = "The request not found!";
    public static final String PARAMS_NULL_MSG = "Param is null!";
    public static final String PARAMS_ERROR_MSG = "Param is error!";
    public static final String USER_BALANCE_NOT_ENOUGH_MSG = "用户余额不足";
    public static final String USER_NOT_FOUND_MSG ="用户不存在";
    public static final String USER_ALREADY_EXIST_MSG ="用户已存在";
    public static final String PASSWORD_ERROR_MSG ="密码错误";
    public static final String VIP_CAN_NOT_DEGRADE_MSG ="vip不能反向升级";
    public static final String BOOK_NOT_FOUND_MSG ="图书不存在";
    public static final String BOOK_NOT_ENOUGH_MSG ="图书库存不足";
    public static final String DESSERT_NOT_FOUND_MSG ="甜点不存在";
    public static final String DESSERT_NOT_ENOUGH_MSG ="甜点库存不足";
    public static final String BOOK_ALREADY_EXIST_MSG="图书已存在";
    //图片为空
    public static final String IMAGE_IS_NULL_MSG="图片为空";
    //员工已存在
    public static final String ADMIN_ALREADY_EXIST_MSG="员工已存在";
    //员工不存在
    public static final String ADMIN_NOT_FOUND_MSG="员工不存在";
    //经理不存在
    public static final String MANAGER_NOT_FOUND_MSG="经理不存在";
    //经理已存在
    public static final String MANAGER_ALREADY_EXIST_MSG="经理已存在";
    //未购买甜品且非会员无法入内
    public static final String NOT_BUY_DESSERT_AND_NOT_VIP_MSG="未购买甜品且非会员无法入内";
    //没有空座位
    public static final String NO_SEAT_MSG="没有空座位";
    //甜点已存在
    public static final String DESSERT_ALREADY_EXIST_MSG="甜点已存在";
    //收藏图书已存在
    public static final String FAVORITE_BOOK_ALREADY_EXIST_MSG="收藏图书已存在";

}
