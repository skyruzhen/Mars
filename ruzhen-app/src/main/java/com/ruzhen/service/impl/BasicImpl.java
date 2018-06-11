package com.ruzhen.service.impl;

import com.ruzhen.service.Basic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 〈一句话功能简述〉<br>
 * 〈Basic的实现类〉
 *
 * @author lizhen
 * @create 2018/6/8
 * @since 1.0.0
 */
public class BasicImpl implements Basic {
    private static final Logger LOGGER = LoggerFactory.getLogger(BasicImpl.class);
    public String sayHello(String name) {
        LOGGER.info(name);
        return name+": hello!";
    }

}