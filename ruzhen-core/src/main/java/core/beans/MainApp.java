package core.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 〈一句话功能简述〉<br>
 * 〈getBean()〉
 *
 * @author lizhen
 * @create 2018/7/12
 * @since 1.0.0
 */
public class MainApp {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("core/Beans.xml");
        context.start();
        HelloWorld obj = (HelloWorld) context.getBean("helloworld");
        System.out.println(obj.getMessage());
//        context.registerShutdownHook();
        context.stop();
    }
}