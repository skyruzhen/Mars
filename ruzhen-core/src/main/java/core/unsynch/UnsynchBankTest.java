package core.unsynch;

import java.util.Vector;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 〈一句话功能简述〉<br>
 * 〈竞争条件-测试〉
 *
 * @author lizhen
 * @create 2018/10/16
 * @since 1.0.0
 */
public class UnsynchBankTest {
    public static final int NACCOUNTS = 100;
    public static final double INITIAL_BALANCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;

    static PriorityBlockingQueue<Transfer> queue = new PriorityBlockingQueue<>();

    public static void main(String[] args) {
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        for(int j = 0; j < NACCOUNTS; j++){
            //模拟多次请求
            Runnable r = ()->{
                try {
                    for(int i = 0; i <NACCOUNTS; i++) {
                        int fromAccount = i;
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        Transfer transfer = new Transfer();
                        transfer.setAmount(amount);
                        transfer.setFromAccount(fromAccount);
                        transfer.setToAccount(toAccount);
                        queue.put(transfer);
                        Thread.sleep((long) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            Thread option = new Thread(r);
            option.start();
        }


        Runnable t = ()->{
            while (true){
                try {
                    Transfer transfer = queue.take();
                    bank.transfer(transfer.getFromAccount(), transfer.getToAccount(),transfer.getAmount());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread auto = new Thread(t);
        auto.start();
        //原子操作类
//      AtomicInteger

    }
}