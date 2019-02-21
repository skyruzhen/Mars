package core.Spring.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 〈一句话功能简述〉<br>
 * 〈后置处理器〉
 *
 * @author lizhen
 * @create 2018/7/12
 * @since 1.0.0
 */
public class InitHelloWorld implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeforeInitialization: "+beanName);
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("AfterInitialization: "+beanName);
        return bean;
    }
}