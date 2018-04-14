package com.liang.demo.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by 永远有多远 on 2018/4/14.
 */
@Data
public class PsDevice {
    private Integer deId;
    private Integer psId;
    private String psDevName;
    private Integer psDevState;
    //最近连接或者退出时间
    private Timestamp psLastRecvtime;
    private Integer psLineNum;
    private Integer yn;

}
