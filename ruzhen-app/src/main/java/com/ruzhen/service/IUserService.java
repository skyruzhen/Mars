package com.ruzhen.service;

import com.ruzhen.pojo.UserInfo;

/**
 * <p>
 *  用户服务类
 * </p>
 *
 * @author lizhen
 * @since 2018-07-06
 * */
public interface IUserService {
    UserInfo selectOne(String username);
}
