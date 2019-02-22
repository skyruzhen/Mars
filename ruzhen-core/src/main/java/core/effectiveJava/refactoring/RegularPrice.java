package core.effectiveJava.refactoring;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/10/22
 * @since 1.0.0
 */
public class RegularPrice extends Price {
    public double getCharge(int daysRented) {
        double thisAmount = 0;
        //计算积分
        if (daysRented > 2) {
            thisAmount += (daysRented - 2) * 1.5;
        }
        return thisAmount;
    }

    @Override
    public int getPriceCode() {
        return 0;
    }
}