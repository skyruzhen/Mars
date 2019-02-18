package core.multiThreads.ch_5;

import core.util.Tools;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TimeoutWaitWithCondition {
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static boolean ready = false;
    protected static final Random random = new Random();

    public static void main(String[] args) {
        Thread t = new Thread(){
            @Override
            public void run() {
                for(;;){
                    lock.lock();
                    try{
                        ready = random.nextInt(100) < 5 ? true:false;
                        if(ready){
                            condition.signal();
                        }
                    }finally {
                        lock.unlock();
                    }
                 //使当前线程暂停一段随机时间
                    Tools.randomPause(500);
                }//for 循环结束
            }
        };
        t.setDaemon(true);
        t.start();
        waiter(1000);
    }

    public static void waiter(final long timeOut){
        if(timeOut < 0){
            throw new IllegalArgumentException();
        }
        //计算等待的最后期限
        final Date deadLine = new Date(System.currentTimeMillis()+timeOut);
        //是否继续等待
        boolean continueToWait = true;
        lock.lock();
        try {
        while(!ready){
            System.out.println("still not ready, continue to wait:"+continueToWait);
            if(!continueToWait){
                System.out.println("Wait timed out, unable to execution target action!");
                return;
            }

                continueToWait = condition.awaitUntil(deadLine);

         }//while循环结束
            //执行目标动作
            guarededAction();
        } catch (InterruptedException e) {
            lock.unlock();
        }
    }
    private static void guarededAction(){
        System.out.println("Take some action.");
    }
}
