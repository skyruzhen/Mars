package com.ruzhen.ruzhenboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class HelloController {
    @RequestMapping("/")
    String index(){
        HashMap map = new HashMap();
        return "Hello Spring Boot";
    }
}
