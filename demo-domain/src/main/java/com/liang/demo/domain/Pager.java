package com.liang.demo.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by 永远有多远 on 2018/3/20.
 */
@Data
public class Pager {

    private Integer now;
    private Integer num;
    private List list;

}
