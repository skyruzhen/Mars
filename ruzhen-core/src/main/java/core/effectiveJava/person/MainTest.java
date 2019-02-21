package core.effectiveJava.person;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/10/23
 * @since 1.0.0
 */
public class MainTest {
    public static void main(String[] args) {
     /*   PersonNew p = new PersonNew(new Date());
        long startTime = System.currentTimeMillis();
        for(double i = 0; i < 1000000000; i ++){
          p.isBabyBoomer();
        }
        //优化前 463891ms
        //优化后 3314ms
        System.out.println(System.currentTimeMillis() - startTime);*/
        Long sum = 0L;
        long startTime = System.currentTimeMillis();
        for(long i = 0; i < Integer.MAX_VALUE; i++){
            sum += i;
        }
        System.out.println(sum);
        //9737
        System.out.println(System.currentTimeMillis() - startTime);
    }
}