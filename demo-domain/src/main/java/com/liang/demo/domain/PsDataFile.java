package com.liang.demo.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by 永远有多远 on 2018/4/13.
 */
@Data
public class PsDataFile {
    private Integer daId;
    private Integer psId;
    private String  daTestSubject;
    private String daDataDocu;
    private String testType;
    private Integer testNum;
    private String phoneId;
    private String daNote;
    private Integer daState;
    private String testStaff;
    private Integer yn;
    //创建时间
    private Timestamp created;
}
