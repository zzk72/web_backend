package com.example.web_backend.entity;

import com.example.web_backend.config.StateConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageEntity<T>  implements Serializable {

    private int code;//响应码
    private boolean success;
    private String errorMsg;//错误信息
    private T data;//数据
    public static <T> MessageEntity<T> error(int code, String errorMsg){
        MessageEntity<T> messageEntity = new MessageEntity<>();
        messageEntity.setCode(code);
        messageEntity.setSuccess(false);
        messageEntity.setErrorMsg(errorMsg);
        return messageEntity;
    }

    public static <T> MessageEntity<T> error(int code){
        MessageEntity<T> messageEntity = new MessageEntity<>();
        messageEntity.setCode(code);
        messageEntity.setSuccess(false);
        return messageEntity;
    }
    public static <T> MessageEntity<T> success(T data){
        MessageEntity<T> messageEntity = new MessageEntity<>();
        messageEntity.setCode(StateConstant.SUCCESS_CODE);
        messageEntity.setSuccess(true);
        messageEntity.setData(data);
        return messageEntity;
    }
    public static MessageEntity<Void> success(){
        MessageEntity<Void> messageEntity = new MessageEntity<>();
        messageEntity.setCode(StateConstant.SUCCESS_CODE);
        messageEntity.setSuccess(true);
        return messageEntity;
    }

}
