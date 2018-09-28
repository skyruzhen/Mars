package core.CoreJava.GenericClass;

import java.time.LocalDate;

/**
 * 〈一句话功能简述〉<br>
 * 〈方法的擦除带来的两个复杂问题〉
 *
 * @author lizhen
 * @create 2018/9/28
 * @since 1.0.0
 */
public class DateInterval extends Pair<LocalDate> {

    public void setSecond(LocalDate second) {
        if(second.compareTo(getFirst()) > 0)
        super.setSecond(second);
    }
}