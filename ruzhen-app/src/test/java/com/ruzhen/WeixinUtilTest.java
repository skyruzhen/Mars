package com.ruzhen;

import com.alibaba.fastjson.JSONObject;
import com.ruzhen.utils.WeixinUtil;
import core.wechat.po.AccessToken;
import org.junit.Test;

import java.io.IOException;

public class WeixinUtilTest {

    @org.junit.Test
    public void getAccessToken() {
        AccessToken token = WeixinUtil.getAccessToken();
        System.out.println(token.getAccess_token());
        System.out.println(token.getExpires_in());
    }

    @Test
    public void pushMenu(){
        AccessToken token = WeixinUtil.getAccessToken();
        String menu = JSONObject.toJSONString(WeixinUtil.initMenu());
        int result = WeixinUtil.createMenu(token.getAccess_token(), menu);
        if(result == 0){
            System.out.println("创建菜单成功");
        }else{
            System.out.println("创建菜单失败："+result);
        }

    }

    @Test
    public void pushMedia(){
        AccessToken token = WeixinUtil.getAccessToken();
        String path ="C:\\Users\\Administrator\\Music\\虾米音乐\\zuimeideqidai.mp3";
        try {
            String mediaId = WeixinUtil.upload(path, token.getAccess_token(), "thumb");
            System.out.println(mediaId);
            /*{"media_id":"gHtqcuZ0Drp65mrEqvb56i8G3tKBkE-X2xCs3G4-l4TKIWscD0RdFTVSS1QWz3_V","created_at":1529895606,"type":"image"}
gHtqcuZ0Drp65mrEqvb56i8G3tKBkE-X2xCs3G4-l4TKIWscD0RdFTVSS1QWz3_V*/
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}