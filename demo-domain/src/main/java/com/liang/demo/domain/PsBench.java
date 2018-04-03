package com.liang.demo.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by 永远有多远 on 2018/3/30.
 */
@Data
public class PsBench {
    private Integer psId;
    private String  psName;
    private Integer psStop;
    private Integer psAlarm;
    //是否有效
    private Integer yn;
    //创建时间
    private Timestamp created;
    //修改时间
    private Timestamp modified;
}
