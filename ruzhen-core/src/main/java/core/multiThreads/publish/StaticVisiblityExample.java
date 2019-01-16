package core.multiThreads.publish;

import java.util.HashMap;
import java.util.Map;

public class StaticVisiblityExample {
    private static Map<String, String> taskConfig;
    static{
        System.out.println("The class being initialized...");
        taskConfig = new HashMap<String, String>();
        taskConfig.put("url", "https://github.com/Viscent");
        taskConfig.put("timeout", "1000");
    }
    public static void changeConfig(String url, int timeout){
        taskConfig = new HashMap<>();
        taskConfig.put("url", url);
        taskConfig.put("timeout",String.valueOf(timeout));
    }
    public static void init(){
        Thread t = new Thread(()->{
           String url = taskConfig.get("url");
           String timeout = taskConfig.get("timeout");
           doTask(url, Integer.valueOf(timeout));
        });
    }

    private static void doTask(String url, Integer integer) {
        //省略其他代码
        //模拟实际操作的耗时
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
