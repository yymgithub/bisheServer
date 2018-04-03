package com.liang.demo.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by 永远有多远 on 2018/4/3.
 */
@Data
public class PsDeviceAlarm {
    private  Integer alarmId;
    private  Integer psId;
    //是否有效
    private Integer yn;
    //创建时间
    private Timestamp created;
    //0-正在报警 2-报警解除
    private Integer deState;
}
