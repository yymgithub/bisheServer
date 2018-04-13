package com.liang.demo.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by 永远有多远 on 2018/4/11.
 */
@Data
public class PsLoad {
    private Integer loId;
    private Integer psId;
    private Integer loMode;
    private Integer loRamptime;
    private double lo1Speed;
    private Integer lo1Reverse;
    private Integer lo1Remote;
    private double lo2Speed;
    private Integer lo2Reverse;
    private Integer lo2Remote;
    private String phoneId;
    //是否有效
    private Integer yn;
    //创建时间
    private Timestamp created;

}
