package com.ruzhen.controller;

import com.alibaba.fastjson.JSONObject;
import core.po.TextMessage;
import core.util.CheckUtil;
import core.util.MessageUtil;
import core.util.WeixinUtil;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈验证消息的确来自微信服务器〉
 *
 * @author lizhen
 * @create 2018/6/21
 * @since 1.0.0
 */
@Controller
@RequestMapping("/weixin")
public class WeixinController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @RequestMapping(value = "/wx.html", method = RequestMethod.GET)
    public void weixinCall(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //微信服务器将发送GET请求到填写的服务器地址URL上，GET请求携带参数如下
        logger.info("------------微信服务器将发送GET请求到填写的服务器地址URL上，GET请求携带参数如下----------");

        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        logger.info("signature:{}, timestamp:{}, nonce:{}, echostr:{}",signature, timestamp, nonce,echostr);
        if(CheckUtil.checkSignature(signature,timestamp,nonce)){
            logger.info("校验成功！");
            response.getWriter().print(echostr);
        }
    }

    @RequestMapping(value = "/wx.html", method = RequestMethod.POST)
    public void weixinCallback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        logger.info("------------微信服务器将发送POST请求到填写的服务器地址URL上，POST请求携带参数如下----------");
        try {
            Map<String, String> map = MessageUtil.xmlToMap(request);
            String fromUserName = map.get("FromUserName");
            String ToUserName = map.get("ToUserName");
            String MsgType = map.get("MsgType");
            String Content = map.get("Content");
            String message = null;
            logger.info("{}", map.toString());
            message = MessageUtil.initText(ToUserName,fromUserName, "MsgType"); //默认回复
            if(MessageUtil.MESSAGE_TEXT.equals(MsgType)){
                if("1".equals(Content)){
                    message = MessageUtil.initText(ToUserName,fromUserName, "1");
                }else if("2".equals(Content)) {
                    message = MessageUtil.initNewsMessage(ToUserName, fromUserName);
                }else if("3".equals(Content)){
                    message = MessageUtil.initImageMessage(ToUserName, fromUserName);
                }else{
                    TextMessage text = new TextMessage();
                    text.setFromUserName(ToUserName);
                    text.setToUserName(fromUserName);
                    text.setMsgType("text");
                    text.setCreateTime(new Date().getTime());
                    text.setContent("您发送的消息是："+Content);
                    message = MessageUtil.textMassageToXml(text);
                }
            }else if(MessageUtil.MESSAGE_EVENT.equals(MsgType)){
                String eventType =  map.get("Event");
                if (MessageUtil.MESSAGE_SUBSCIRBE.equals(eventType)){
                    message = MessageUtil.initText(ToUserName, fromUserName, MessageUtil.menuText());
                }else if(MessageUtil.MESSAGE_VIEW.equals(eventType)){
                    String url = map.get("EventKey");
                    message = MessageUtil.initText(ToUserName, fromUserName, url);
                }else if(MessageUtil.MESSAGE_SCANCODE.equals(eventType)){
                    String key = map.get("EventKey");
                    message = MessageUtil.initText(ToUserName, fromUserName, key);
                }
            }else if(MessageUtil.MESSAGE_LOCATION.equals(MsgType)){
                String label = map.get("Label");
                message = MessageUtil.initText(ToUserName, fromUserName, label);
            }
            logger.info("处理完成，返回结果是：message={}",message);
            response.getWriter().print(message);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/callback.html", method = RequestMethod.GET)
    public String authCallback(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String code = request.getParameter("code");
        logger.info("微信授权成功回调：code={}",code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid" +
                "=" +WeixinUtil.AppID+
                "&secret=" +WeixinUtil.AppSecret+
                "&code=" +code+
                "&grant_type=authorization_code";
        JSONObject jsonObject = WeixinUtil.doGetStr(url);
        logger.info("获取access_token：{}", jsonObject);
        /*
        * { "access_token":"ACCESS_TOKEN",
            "expires_in":7200,
            "refresh_token":"REFRESH_TOKEN",
            "openid":"OPENID",
            "scope":"SCOPE" }
        */
        String openId = jsonObject.getString("openid");
        String accessToken = jsonObject.getString("access_token");

        //如果超时，通过刷新重新获取access_token
        // ....

        //拉取用户信息
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?" +
                "access_token=" +accessToken+
                "&openid=" +openId+
                "&lang=zh_CN";

        JSONObject userInfo = WeixinUtil.doGetStr(infoUrl);

        logger.info("用户信息如下："+userInfo);
        model.addAttribute("userinfo", userInfo);
        return "UserInfo";
    }

}