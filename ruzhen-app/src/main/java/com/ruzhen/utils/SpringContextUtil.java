package com.ruzhen.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * 〈常规类获取bean的工具类〉
 *
 * @author lizhen
 * @create 2018/7/10
 * @since 1.0.0
 */

@Component
public class SpringContextUtil implements ApplicationContextAware {
    private  Logger logger = LoggerFactory.getLogger(this.getClass());

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    private static ApplicationContext applicationContext = null;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.info("------SpringContextUtil setApplicationContext-------");
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    public static <T> T getBeanByName(Class<T> clazz) throws BeansException {
        try {
            char[] cs=clazz.getSimpleName().toCharArray();
            cs[0] += 32;//首字母大写到小写
            return (T) applicationContext.getBean(String.valueOf(cs));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }


    }