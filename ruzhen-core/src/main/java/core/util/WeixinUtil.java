package core.util;

import com.alibaba.fastjson.JSONObject;
import core.menu.Button;
import core.menu.ClickButton;
import core.menu.Menu;
import core.menu.ViewButton;
import core.po.AccessToken;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * 〈一句话功能简述〉<br>
 * 〈微信公共参数〉
 *
 * @author lizhen
 * @create 2018/6/22
 * @since 1.0.0
 */
public class WeixinUtil {
    //测试账号
    public static final String AppID = "wxdc6b6f5e8429a4a6";
    public static final String AppSecret = "12fa4c15df231e6b147da1f835ca133e";
    public static final String ACCESS_TOKEN_URL ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    public static final String CREATE_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * get请求
     * @param url
     * @return
     */
    public static JSONObject doGetStr(String url){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            CloseableHttpResponse response = httpclient.execute(httpget);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            jsonObject = JSONObject.parseObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * post请求
     * @param url
     * @param outStr
     * @return
     */
    public static JSONObject doPostStr(String url, String outStr){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = null;

        httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
        try {
            HttpResponse response = httpclient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            jsonObject = JSONObject.parseObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;

    }

    /**
     * 获取accesstoken
     * @return
     */
    public static AccessToken getAccessToken(){
        AccessToken token = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("APPID",AppID ).replace("APPSECRET", AppSecret);
        JSONObject jsonObject = doGetStr(url);
        if(jsonObject != null){
            token.setAccess_token(jsonObject.getString("access_token"));
            token.setExpires_in(jsonObject.getInteger("expires_in"));
        }
        return token;
    }

    /**
     * 自定义菜单推送
     * @return
     */
    public static Menu initMenu(){
        Menu menu = new Menu();
        ClickButton button11 = new ClickButton();
        button11.setName("click菜单");
        button11.setType("click");
        button11.setKey("1");

        ViewButton button21 = new ViewButton();
        button21.setName("view菜单");
        button21.setType("view");
        button21.setUrl("http://www.imooc.com");

        ClickButton button31 = new ClickButton();
        button31.setName("扫码事件");
        button31.setType("scancode_push");
        button31.setKey("31");

        ClickButton button32 = new ClickButton();
        button32.setName("地理位置");
        button32.setType("location_select");
        button32.setKey("32");

        Button button = new Button();
        button.setName("菜单");
        button.setSub_button(new Button[]{button31, button32});

        menu.setButton(new Button[]{button11, button21, button});
        return menu;
    }

    public static int createMenu(String token, String menu){
       int result = 0;
        String url =CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = doPostStr(url,menu);
        if(jsonObject != null){
            result = jsonObject.getInteger("errcode");
        }
        return result;
    }

/*    public static JSONObject doGetJson(String url){
        JSONObject jsonObject = null;
        CloseableHttpClient httpClient =  HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            jsonObject = JSONObject.parseObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonObject;

    }*/

}