package core.Refactoring.case1;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/10/22
 * @since 1.0.0
 */
public class ChildrensPrice extends Price{
    public double getCharge(int daysRented) {
        double thisAmount = 0;
        //计算积分

        thisAmount += 1.5;
        if (daysRented > 3) {
            thisAmount += (daysRented - 3) * 1.5;
        }

        return thisAmount;
    }

    @Override
    public int getPriceCode() {
        return Movie.CHILDRENS;
    }
}