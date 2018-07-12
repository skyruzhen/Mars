package core.beans;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;

/**
 * 〈一句话功能简述〉<br>
 * 〈结束监听事件〉
 *
 * @author lizhen
 * @create 2018/7/12
 * @since 1.0.0
 */
public class CStopEventHandler implements ApplicationListener<ContextStoppedEvent> {

    public void onApplicationEvent(ContextStoppedEvent contextStoppedEvent) {
        System.out.println("ContextStoppedEvent Received");
    }
}