package com.liang.demo.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by 永远有多远 on 2018/4/15.
 */
@Data
public class PsLog {
    private Integer logId;
    private Integer psId;
    private String logErrorMsg;
    private Integer yn;
    //创建时间
    private Timestamp logTime;
}
