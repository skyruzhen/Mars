package com.ruzhen.filter;

import com.ruzhen.wrapper.MyCharacterEncodingRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;

/**
 * 〈一句话功能简述〉<br>
 * 〈自定义http的get,post请求中参数中文乱码问题〉
 *
 * @author lizhen
 * @create 2018/9/17
 * @since 1.0.0
 */
public class MyCharacterEncodingFilter implements Filter {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private FilterConfig filterConfig = null;

    //设置默认的字符编码
    private String defaultCharset = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOGGER.info("----MyCharacterEncodingFilter执行----");
        MyCharacterEncodingRequest myCharacterEncodingRequest = new MyCharacterEncodingRequest((HttpServletRequest) servletRequest);
        filterChain.doFilter(myCharacterEncodingRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}