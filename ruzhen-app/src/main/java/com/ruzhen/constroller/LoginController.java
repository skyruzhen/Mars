package com.ruzhen.constroller;

import core.util.WeixinUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 〈一句话功能简述〉<br>
 * 〈公众号授权登录〉
 *
 * @author lizhen
 * @create 2018/6/22
 * @since 1.0.0
 */
@Controller
@RequestMapping("/wxLogin")
public class LoginController {

    @RequestMapping("/auth.html")
    public void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        String backUrl = "http://ruzhen.free.ngrok.cc/ruzhen-app/weixin/callback.html";
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=" +WeixinUtil.AppID+
                "&redirect_uri="+URLEncoder.encode(backUrl)+
                "&response_type=code&scope=snsapi_userinfo" +
                "&state=" +"callback"+
                "#wechat_redirect";
        response.sendRedirect(url);
    }

}