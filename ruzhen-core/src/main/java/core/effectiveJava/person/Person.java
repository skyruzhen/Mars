package core.effectiveJava.person;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 〈一句话功能简述〉<br>
 * 〈避免创建不必要的对象〉
 *
 * @author lizhen
 * @create 2018/10/23
 * @since 1.0.0
 */
public class Person {
    private final Date birthDate;
    public Person(Date birthDate){
        this.birthDate = birthDate;
    }
    //Other fields, methods, and constructor omitted
    public boolean isBabyBoomer(){
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT8"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomStart = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        Date boomEnd = gmtCal.getTime();
        return birthDate.compareTo(boomStart) >= 0 &&
                birthDate.compareTo(boomEnd) < 0;
    }
}