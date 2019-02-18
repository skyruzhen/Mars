package concurrent.chapter07;

import java.util.concurrent.TimeUnit;

public class CaptureThreadException {
    public static void main(String[] args) {
        //① 设置回调接口
        Thread.setDefaultUncaughtExceptionHandler((t, e)->{
            System.out.println(t.getName() + " occur exception");
            e.printStackTrace();
        });
        final Thread thread = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //② 这里会出现unchecked异常
            System.out.println(1/0);
        }, "Test-Thread");//Thread定义结束
        thread.start();
    }
}
