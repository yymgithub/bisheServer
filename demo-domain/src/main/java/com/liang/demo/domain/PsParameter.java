package com.liang.demo.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by 永远有多远 on 2018/4/1.
 */
@Data
public class PsParameter {
    private Integer paraId;
    private Integer psId;
    private String paraName;
    private double paraValue;
    //参数单位
    private String paraUnit;
    //参数小数位
    private Integer paraFormat;
    //是否有效
    private Integer yn;
    //创建时间
    private Timestamp created;
    //修改时间
    private Timestamp modified;


}
