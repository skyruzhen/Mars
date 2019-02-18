package com.ruzhen.service.util;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class HttpParameters {
    public static void main(String[] args) throws UnsupportedEncodingException {
       //HttpClient3 MultiThreadedHttpConnectionManager
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("account", ""));
        formparams.add(new BasicNameValuePair("password",""));
        HttpEntity reqEntity = new UrlEncodedFormEntity(formparams, "utf-8");
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000)
                .setConnectionRequestTimeout(5000).build();
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("http://www.baidu.com");
        post.setEntity(reqEntity);
        post.setConfig(requestConfig);
        try {
            HttpResponse response = client.execute(post);
            if(response.getStatusLine().getStatusCode() == 200){
                HttpEntity resEntity = response.getEntity();
                String message = EntityUtils.toString(resEntity);
                System.out.println(message);
            }else{
                System.out.println("请求失败 "+response.getStatusLine().getStatusCode() );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
