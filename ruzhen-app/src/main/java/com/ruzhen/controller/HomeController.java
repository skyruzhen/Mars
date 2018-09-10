package com.ruzhen.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruzhen.pojo.UserInfo;
import com.ruzhen.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * 〈一句话功能简述〉<br>
 * 〈首页〉
 *
 * @author lizhen
 * @create 2018/6/14
 * @since 1.0.0
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserService iUserService;

    @ApiOperation(value="一个测试API",notes = "第一个测试api")
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index(Model model) {
        return "login";
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.POST)
    public String login(UserInfo userInfo) {
        JSONObject jsonObject = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUsername(), userInfo.getPassword());
        try {
            subject.login(token);
            jsonObject.put("token", subject.getSession().getId());
            jsonObject.put("msg", "登录成功");
            jsonObject.put("code", 200 );
        } catch (IncorrectCredentialsException e) {
            jsonObject.put("msg", "密码错误");
            jsonObject.put("code", 2001 );
        } catch (LockedAccountException e) {
            jsonObject.put("msg", "登录失败，该用户已被冻结");
            jsonObject.put("code", 2002 );
        } catch (AuthenticationException e) {
            jsonObject.put("msg", "该用户不存在");
            jsonObject.put("code", 2003);
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("msg", e.getMessage());
            jsonObject.put("code", 9999);
        }
        logger.info("{} login... {}",userInfo.getUsername(), jsonObject.get("msg"));
        return jsonObject.toJSONString();
    }

}