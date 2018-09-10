package core.CoreJava;

import java.util.Arrays;

/**
 * 〈一句话功能简述〉<br>
 * 〈数组拷贝〉
 *
 * @author lizhen
 * @create 2018/9/9
 * @since 1.0.0
 */
public class ArrayCopy {
    public static void main(String[] args) {
        int[] luckyNumbers = {17, 19, 23, 29, 31, 37};
        int[] smallPrimers = luckyNumbers;
        for(int i: smallPrimers){
            System.out.println(i);
        }

        Arrays.sort(smallPrimers);
    }
}