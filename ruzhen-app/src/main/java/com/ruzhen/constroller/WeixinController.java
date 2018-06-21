package com.ruzhen.constroller;

import core.po.TextMessage;
import core.util.CheckUtil;
import core.util.MessageUtil;
import org.dom4j.DocumentException;
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

    @RequestMapping(value = "/wx.html", method = RequestMethod.GET)
    public void weixinCall(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //微信服务器将发送GET请求到填写的服务器地址URL上，GET请求携带参数如下
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");

        if(CheckUtil.checkSignature(signature,timestamp,nonce)){
            response.getWriter().print(echostr);
        }
    }

    @RequestMapping(value = "/wx.html", method = RequestMethod.POST)
    public void weixinCallback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            Map<String, String> map = MessageUtil.xmlToMap(request);
            String fromUserName = map.get("FromUserName");
            String ToUserName = map.get("ToUserName");
            String MsgType = map.get("MsgType");
            String Content = map.get("Content");
            String message = null;
            if(MessageUtil.MESSAGE_TEXT.equals(MsgType)){
                TextMessage text = new TextMessage();
                text.setFromUserName(ToUserName);
                text.setToUserName(fromUserName);
                text.setMsgType("text");
                text.setCreateTime(new Date().toString());
                text.setContent("您发送的消息是："+Content);
                message = MessageUtil.textMassageToXml(text);
            }else if(MessageUtil.MESSAGE_EVENT.equals(MsgType)){
                String eventType =  map.get("Event");
                if (MessageUtil.MESSAGE_SUBSCIRBE.equals(eventType)){
                    message = MessageUtil.initText(ToUserName, fromUserName, MessageUtil.menuText());
                }
            }
            response.getWriter().print(message);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

}