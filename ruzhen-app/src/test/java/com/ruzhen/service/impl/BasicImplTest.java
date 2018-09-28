package com.ruzhen.service.impl;

import com.caucho.hessian.client.HessianProxyFactory;
import com.ruzhen.service.Basic;
import org.junit.Test;

import java.net.MalformedURLException;

public class BasicImplTest {

    @Test
    public void sayHello() {
        String url = "http://localhost:8082/ruzhen-app/hessian";
        HessianProxyFactory factory = new HessianProxyFactory();
        factory.setOverloadEnabled(true);
        try {
            Basic basic = (Basic) factory.create(Basic.class, url);
            System.out.println(basic.sayHello("ruzhen"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}