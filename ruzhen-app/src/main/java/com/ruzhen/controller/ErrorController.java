package com.ruzhen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 〈一句话功能简述〉<br>
 * 〈404错误相关错误页面〉
 *
 * @author lizhen
 * @create 2018/6/19
 * @since 1.0.0
 */
@Controller
@RequestMapping("error")
public class ErrorController{
    @RequestMapping("404")
    public String handler1(Model mode){
        return "statics/404";
    }
}