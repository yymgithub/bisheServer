package com.liang.demo.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by 永远有多远 on 2018/4/15.
 */
@Data
public class PsTestRecord {
    private Integer testId;
    private Integer psId;
    private String testPara;
    //是否有效
    private Integer yn;
    //创建时间
    private Timestamp created;
}
