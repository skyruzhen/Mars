package com.ruzhen.pojo;

import java.io.Serializable;

/**
 * @author lizhen
 * 2018年7月4日 17:05:51
 */
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userid;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private int departmentid;
    private String email;
    private int salt;
    private String active;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname=" + lastname +
                ", departmentid='" + departmentid + '\'' +
                ", email='" + email + '\'' +
                ", salt='" + salt + '\'' +
                ", active='" + active + '\'' +
                '}';
    }

}
