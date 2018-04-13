package com.liang.demo.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by 永远有多远 on 2018/4/13.
 */
@Data
public class PsFile {
    private Integer fileId;
    private String fileName;
    private String fileTestType;
    private Integer psId;
    private Integer fileState;
    private Integer yn;
    //创建时间
    private Timestamp created;
}
