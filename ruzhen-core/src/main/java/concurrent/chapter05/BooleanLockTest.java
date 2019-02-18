package concurrent.chapter05;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

import static java.lang.Thread.currentThread;
import static java.util.concurrent.ThreadLocalRandom.current;

public class BooleanLockTest {
    private final Lock lock = new BooleanLock();

    public void syncMethod(){
        try {
            lock.lock(1000);
            int randomInt = current().nextInt(10);
            System.out.println(currentThread()+" get the lock. will release after "+randomInt+" seconds");
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    public static void main0(String[] args) {
        BooleanLockTest blt = new BooleanLockTest();
        //定义一个线程并且启动
        IntStream.range(0, 10).mapToObj(i->new Thread(blt::syncMethod)).forEach(Thread::start);
    }

    public static void main(String[] args) throws InterruptedException {
        BooleanLockTest blt = new BooleanLockTest();
        new Thread(blt::syncMethod, "T1").start();
        TimeUnit.MILLISECONDS.sleep(2);
        Thread t2 = new Thread(blt::syncMethod, "T2");
        t2.start();
        TimeUnit.MILLISECONDS.sleep(10);
       // t2.interrupt();
    }
}
