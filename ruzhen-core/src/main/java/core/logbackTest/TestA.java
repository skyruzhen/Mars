package core.logbackTest;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/11/12
 * @since 1.0.0
 */
public class TestA {
    static ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
    static ScheduledExecutorService singleThreadPool = Executors.newSingleThreadScheduledExecutor();
    static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
    final static URL confUrl = TestA.class.getClassLoader().getResource("logback.xml");

    public static void main(String[] args) {
        System.out.println("start ----");
        initLogback();

        ARunnable aR1 = new ARunnable("a-n-1", false, -1);
        ARunnable aR2 = new ARunnable("a-n-2", true, 5);

        for (int i = 0; i < 1000; i++){
            //BRunnable bR1 = new BRunnable("b-n-1"+i, false, -1);
            BRunnable bR2 = new BRunnable("b-n-2"+i, true, 7);

            testFixedThreadPool(bR2,bR2);
        }

    }

    public static void initLogback() {
        try {
            LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
            JoranConfigurator configurator = new JoranConfigurator();
            configurator.setContext(lc);
            lc.reset();
            configurator.doConfigure(confUrl);
            StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
        } catch (JoranException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void testUseSheduledThreadPool(Runnable r1, Runnable r2) {
        threadPool.scheduleAtFixedRate(r1, 1, 20, TimeUnit.MILLISECONDS);
        threadPool.scheduleAtFixedRate(r2, 1, 20, TimeUnit.MILLISECONDS);
    }

    public static void testUseThread(Runnable r1, Runnable r2){
        new Thread(r1).start();
        new Thread(r2).start();
    }
    public static void testFixedThreadPool(Runnable r1, Runnable r2){
        fixedThreadPool.execute(r1);
        fixedThreadPool.execute(r2);
    }

    //线程循环调度
    public static class ARunnable implements Runnable{
        private final static Logger LOGGER = LoggerFactory.getLogger(ARunnable.class);
        String name; //线程名称
        int count = 0;
        boolean isInterrupt = false;
        int interruptCount;
        public ARunnable(){

        }

        public ARunnable(String name,  boolean isInterrupt, int interruptCount) {
            this.name = name;
            this.isInterrupt = isInterrupt;
            this.interruptCount = interruptCount;
        }

        @Override
        public void run() {
            if(isInterrupt && interruptCount == count){
                Thread.currentThread().interrupt();
                LOGGER.warn("A interrupt .. name:{}, count{}",name, count);
            }else{
                LOGGER.warn("A name:{}, count:{}",name, count++);
            }
        }
    }

    //内部循环执行
    public static class BRunnable implements Runnable{
        private final static Logger LOGGER = LoggerFactory.getLogger(ARunnable.class);

        String name; //线程名称
        int count = 0;
        boolean isInterrupt = false;
        int interruptCount;
        public BRunnable(){

        }

        public BRunnable(String name,  boolean isInterrupt, int interruptCount) {
            this.name = name;
            this.isInterrupt = isInterrupt;
            this.interruptCount = interruptCount;
        }

        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(isInterrupt && interruptCount == count){
                    int a =0;
                    float b =1/a;
//                    Thread.currentThread().interrupt();
                    LOGGER.warn("B interrupt ... name:"+name+", count:"+ count);
                }
                for (int i = 0; i < 1000; i++){
                    //BRunnable bR1 = new BRunnable("b-n-1"+i, false, -1);
                    LOGGER.warn("B name:"+name+",log i:"+i);

                }

                LOGGER.warn("B name:"+name+", count:"+count++);
            }
        }
    }


}