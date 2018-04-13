package com.liang.demo.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by 永远有多远 on 2018/4/11.
 */
@Data
public class PsTemp {
    private Integer teId;
    private Integer psId;
    private String teName;
    private double teValue;
    private String phoneId;
    //是否有效
    private Integer yn;
    //创建时间
    private Timestamp created;
}
