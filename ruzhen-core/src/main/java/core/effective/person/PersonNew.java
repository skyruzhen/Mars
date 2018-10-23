package core.effective.person;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/10/23
 * @since 1.0.0
 */
public class PersonNew {
    private final Date birthDate;
    private static final Date BOOM_START;
    private static final Date BOOM_END;
    static{
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT8"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_START = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_END = gmtCal.getTime();
    }
    public PersonNew(Date birthDate){
        this.birthDate = birthDate;
    }
    //Other fields, methods, and constructor omitted
    public boolean isBabyBoomer(){

        return birthDate.compareTo(BOOM_START) >= 0 &&
                birthDate.compareTo(BOOM_END) < 0;
    }
}