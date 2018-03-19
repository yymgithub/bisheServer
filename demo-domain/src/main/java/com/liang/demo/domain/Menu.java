package com.liang.demo.domain;

import lombok.Data;

import java.util.List;

/**
 * Created by 永远有多远 on 2018/3/19.
 */
@Data
public class Menu {
    private List<Menu> menuVoList;
    private String menuName;
    private String menuLink;

}
