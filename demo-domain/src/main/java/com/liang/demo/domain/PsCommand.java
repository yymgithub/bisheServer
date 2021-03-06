package com.liang.demo.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by 永远有多远 on 2018/4/2.
 */
@Data
public class PsCommand {
    private Integer comId;
    private Integer psId;
    private String comName;
    private String phoneId;
    private Integer comState;
    //是否有效
    private Integer yn;
    //创建时间
    private Timestamp created;
}
