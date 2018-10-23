package core.Refactoring.case1;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/10/22
 * @since 1.0.0
 */
public class NewReleasePrice extends Price{
    public double getCharge(int daysRented) {
        //计算积分
        return daysRented * 3;
    }

    @Override
    public int getPriceCode() {
        return Movie.NEW_RELEASE;
    }
}