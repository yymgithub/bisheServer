package com.liang.demo.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by 永远有多远 on 2018/4/12.
 */
@Data
public class PsFault {
    private Integer faultId;
    private String faultName;
    private Integer psId;
    private String phoneId;
    private Integer paultState;
    //是否有效
    private Integer yn;
    //创建时间
    private Timestamp created;
}
