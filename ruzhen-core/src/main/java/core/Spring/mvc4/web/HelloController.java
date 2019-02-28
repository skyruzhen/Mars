package core.Spring.mvc4.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/index.jsp")   //2 配置URL和方法之间的映射
    public String hello() {
        return "index";
    }
}
