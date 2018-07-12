package com.ruzhen.service.impl;

import com.ruzhen.pojo.UserInfo;
import com.ruzhen.service.IUserService;
import core.util.Utf8;
import org.apache.shiro.crypto.hash.Sha1Hash;
import org.springframework.stereotype.Service;

@Service("userInfoService")
public class UserServiceImpl implements IUserService {

    @Override
    public UserInfo selectOne(String username) {
        //测试数据
        UserInfo userInfo = new UserInfo();
        userInfo.setUserid(1);
        userInfo.setUsername("admin");
        userInfo.setSalt(3936);
        userInfo.setPassword("5724ca6d20fd9f192ccea1eb582291da50505c63");
        return userInfo;
    }


    //TODO
    //测试 将spring security中的密码 转换为 shiro识别的密码
    public static void main(String[] args) {
        String salt = "3936";  //盐值
        byte[] password =  Utf8.encode("admin");
        String result = new Sha1Hash(password,"3396",1).toString();
        System.out.println(result);
    }

    //ehour 密码加密方式 验证并登录系统
    public static void main1(String[] args) {
        String salt = String.valueOf((int)(Math.random()  * 10000));
        System.out.println(salt);
        byte[] password =  Utf8.encode("root{"+salt+"}");

        String result = Utf8.decode(password);
        System.out.println(result);
//        String result = new Sha1Hash(password,"",1).toString();
//        System.out.println(result);
    }
}
