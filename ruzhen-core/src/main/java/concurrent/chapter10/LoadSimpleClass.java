package com.ruzhen.concurrent.chapter10;

public class LoadSimpleClass {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        BrokerDelegateClassLoader classLoader = new BrokerDelegateClassLoader("G\\classloader1");
        Class<?> aClass = classLoader.loadClass("com.ruzhen.concurrent.chapter10.SimpleClass");
        System.out.println(classLoader.getParent());
        aClass.newInstance();
    }
}
