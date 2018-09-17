package com.ruzhen.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;

/**
 * 〈一句话功能简述〉<br>
 * 〈深入理解装饰器模式-在不继承类的情况下增强功能〉
 *
 * @author lizhen
 * @create 2018/9/17
 * @since 1.0.0
 */
public class MyCharacterEncodingRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;

    public MyCharacterEncodingRequest(HttpServletRequest request) {
        super(request);
        this.request  = request;
    }

    /**
     * 需要增强的getParamter方法
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name) {
        try {
            String value = this.request.getParameter(name);
            if (value == null) return null;
            if (!this.request.getMethod().equalsIgnoreCase("get")) {
                return value;
            } else {
                value = new String(value.getBytes("ISO8859-1"), this.request.getCharacterEncoding());
                return value;
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}