package com.ruzhen.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class HelloController {
    @Value("${book.author}")
    private String bookAuthor;

    @Value("${book.name}")
    private String bookName;

    @RequestMapping("/")
    String index(){
        HashMap map = new HashMap();
        return "book name is:"+bookName+" and book author is:"+bookAuthor;
    }
}
