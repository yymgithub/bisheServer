package com.liang.demo.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by 永远有多远 on 2018/4/9.
 */
@Data
public class PsDrive {
    private  Integer drId;
    private  Integer psId;
    private  Integer drMode;
    private  double  drLoad;
    private  Integer drRamptime;
    private  Integer drReverse;
    private  Integer drRemotestatus;
    //是否有效
    private Integer yn;
    //创建时间
    private Timestamp created;
    private String phoneId;

}
