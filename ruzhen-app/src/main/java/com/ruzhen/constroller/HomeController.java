package com.ruzhen.constroller;

import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URL;
import java.util.Properties;


/**
 * 〈一句话功能简述〉<br>
 * 〈首页〉
 *
 * @author lizhen
 * @create 2018/6/14
 * @since 1.0.0
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    @RequestMapping(value = "/index.html", method = RequestMethod.GET)
    public String index(Model model) {
        return "home";
    }

}