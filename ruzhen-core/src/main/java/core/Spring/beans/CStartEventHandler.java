package core.Spring.beans;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

/**
 * 〈一句话功能简述〉<br>
 * 〈开始监听事件〉
 *
 * @author lizhen
 * @create 2018/7/12
 * @since 1.0.0
 */

public class CStartEventHandler implements ApplicationListener<ContextStartedEvent> {


    public void onApplicationEvent(ContextStartedEvent contextStartedEvent) {
        System.out.println("ContextStartedEvent Received");
    }
}