package com.ruzhen.pojo;

import java.io.Serializable;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author lizhen
 * @since 2018-07-06
 */
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private String role;
    private String name;
    private String pid;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
