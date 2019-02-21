package core.multiThreads.unsynch;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/10/17
 * @since 1.0.0
 */
public class Transfer {
    private double amount;
    private int fromAccount;
    private int toAccount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(int fromAccount) {
        this.fromAccount = fromAccount;
    }

    public int getToAccount() {
        return toAccount;
    }

    public void setToAccount(int toAccount) {
        this.toAccount = toAccount;
    }
}