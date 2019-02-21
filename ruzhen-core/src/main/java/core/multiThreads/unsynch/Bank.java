package core.multiThreads.unsynch;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 〈一句话功能简述〉<br>
 * 〈模拟银行-转账操作〉
 *
 * @author lizhen
 * @create 2018/10/16
 * @since 1.0.0
 */
public class Bank {
    private Lock bankLock = new ReentrantLock();
    private Condition sufficientFunds;
    private final double[] accounts;
    public Bank(int n, double initialBalance){
        accounts = new double[n];
        Arrays.fill(accounts, initialBalance);
        sufficientFunds = bankLock.newCondition();
    }

    public void transfer(int from, int to, double amount){
            if(accounts[from] < amount) {
                return;
            }
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf(" %10.2f from %d to %d", amount, from, to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
    }

    private synchronized double getTotalBalance() {
        double sum = 0;
        for(double a:accounts) sum += a;
        return sum;
    }

    public int size(){
        return accounts.length;
    }

}