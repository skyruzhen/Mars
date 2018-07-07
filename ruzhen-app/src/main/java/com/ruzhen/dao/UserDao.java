package com.ruzhen.dao;

import com.ruzhen.pojo.UserInfo;

public interface UserDao {
    String select(Integer id);

    UserInfo selectOne(String username);
}
