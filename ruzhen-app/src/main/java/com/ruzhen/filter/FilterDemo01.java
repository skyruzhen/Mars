package com.ruzhen.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 〈一句话功能简述〉<br>
 * 〈自定义过滤器〉
 *
 * @author lizhen
 * @create 2018/9/17
 * @since 1.0.0
 */
public class FilterDemo01 implements Filter {
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //只初始化一次，在servlet之前初始化
        LOGGER.info("----过滤器初始化----");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //对request和response进行一些预处理
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html;charset=utf-8");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        LOGGER.info("请求的url:{}",httpServletRequest.getRequestURL());
        LOGGER.info("FilterDemo01执行前");

        filterChain.doFilter(servletRequest, servletResponse); //让目标资源放行

        LOGGER.info("FilterDemo01执行后");
    }

    @Override
    public void destroy() {
        LOGGER.info("----过滤器销毁----");
    }
}