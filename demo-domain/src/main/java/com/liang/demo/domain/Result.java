package com.liang.demo.domain;

import lombok.Data;
import lombok.Setter;

@Data
public class Result {

    @Setter
    private boolean isSuccess;
    //返回码，1代表成功，2代表输入参数错误，3代表出现异常，456......
    private Integer code;
    //code码的解释
    private String message;
    //数据
    private Object data;
}
