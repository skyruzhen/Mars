package com.ruzhen.concurrent.chapter10;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //声明一个MyClassLoader
        MyClassLoader classLoader = new MyClassLoader();

        //使用MyClassLoader加载HelloWorld
        Class<?> aClass =classLoader.loadClass("com.ruzhen.concurrent.chapter10.Helloworld");
        System.out.println(aClass.getClassLoader());

        //① 注释
        Object helloWorld = aClass.newInstance();
        System.out.println(helloWorld);
        Method welcomeMethod = aClass.getMethod("welcome");
        String result = (String) welcomeMethod.invoke(helloWorld);
        System.out.println("Result:"+result);

    }
}
