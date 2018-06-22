package core.util;

import com.alibaba.fastjson.JSONObject;
import core.po.AccessToken;
import org.junit.Test;

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

}