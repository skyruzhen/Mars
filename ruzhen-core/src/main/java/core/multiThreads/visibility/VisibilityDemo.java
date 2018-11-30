package core.multiThreads.visibility;

import java.util.concurrent.FutureTask;

/**
 * 〈一句话功能简述〉<br>
 * 〈可见性问题demo〉
 *
 * @author lizhen
 * @create 2018/11/28
 * @since 1.0.0
 */
public class VisibilityDemo {
    public static void main(String[] args) throws InterruptedException {
        TimeConsumingTask task = new TimeConsumingTask();
        Thread thread=new Thread(task);
        thread.start();
        Thread.sleep(10000);
        task.cancel();
    }
}
class TimeConsumingTask implements Runnable{
    private volatile boolean toCancel=false;
    @Override
    public void run() {
        while(!toCancel){
            if(doExecute())
                break;
        }
        if(toCancel){
            System.out.println("Task was canceled.");
        }else{
            System.out.println("Task done.");
        }
    }
    private boolean doExecute() {
        boolean isDone=false;
        System.out.println("executing...");
        //模拟实际操作的时间消耗
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isDone;
    }
    public void cancel(){
        toCancel=true;
        System.out.println(this+" canceled.");
    }
}