package core.CoreJava;

import javax.sound.midi.Soundbank;
import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * 〈一句话功能简述〉<br>
 * 〈日期类 〉
 *
 * @author lizhen
 * @create 2018/9/9
 * @since 1.0.0
 */
public class DateTest {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        int month = localDate.getMonthValue();
        int today = localDate.getDayOfMonth();
        localDate = localDate.minusDays(today - 1);

        DayOfWeek week = localDate.getDayOfWeek();
        int value = week.getValue();

        System.out.println("Mon Tue Wed Thu Fri Sat Sun");
        for (int i = 1; i < value; i++)
            System.out.print("    ");
        while (localDate.getMonthValue() == month) {
            System.out.printf("%3d", localDate.getDayOfMonth());
            if (localDate.getDayOfMonth() == today) {
                System.out.print("*");
            } else {
                System.out.print(" ");
            }
            localDate = localDate.plusDays(1);
            if (localDate.getDayOfWeek().getValue() == 1) System.out.println();
        }
        if (localDate.getDayOfWeek().getValue() != 1) System.out.println();
    }
}