package com.ruzhen.pojo;

import java.io.Serializable;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author lizhen
 * @since 2018-07-06
 */
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String url;  //url地址
    private String name; //url描述

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


