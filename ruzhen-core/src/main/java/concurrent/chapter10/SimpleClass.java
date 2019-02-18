package com.ruzhen.concurrent.chapter10;

import java.util.ArrayList;
import java.util.List;

/**
 * 编译SimpleClass.java文件，将SimpleClass.class复制到D:\\classloader2目录之下，在SimpleClass中，我们访问了java.lang.String
 * java.utils.Lisst以及java.lang.Object等类，这些类都存在于rt.jar包下
 */
public class SimpleClass {
    //在SimpleClass中使用byte[]
    private static byte[] buffer = new byte[8];

    //在SimpleClass中使用String
    private static String str = "";

    //在SimpleClass中使用List
    private static List<String> list = new ArrayList<>();

    static{
        buffer[0] = 1;
        str = "Simple";
        list.add("element");
        System.out.println(buffer[0]);
        System.out.println(str);
        System.out.println(list.get(0));
    }

}
