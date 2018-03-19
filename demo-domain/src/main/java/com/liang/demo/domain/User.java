package com.liang.demo.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by 永远有多远 on 2018/3/13.
 */
@Data
public class User {

    //id
    private Integer id;
    //电话号码
    private String phoneId;
    //用户名称
    private String userName;
    //用户角色
    private Integer userRole;
    //密码
    private String password;
    //用户状态
    private Integer userState;
    //是否有效
    private Integer yn;
    //创建时间
    private Timestamp created;
    //修改时间
    private Timestamp modified;

    public User(String phoneId, String userName, Integer userRole, String password) {
        this.phoneId = phoneId;
        this.userName = userName;
        this.userRole = userRole;
        this.password = password;
    }
    public User(){

    };
}
