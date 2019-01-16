package core.multiThreads.ch_4_practice.case02;

import java.math.BigDecimal;

public class Test {
    public static void main(String[] args) {
        BigDecimal big1 = new BigDecimal("200830527.16");
        Integer a = 10000;
        BigDecimal big2 = new BigDecimal(a.toString());
        BigDecimal big3 = big1.divide(big2, 2, BigDecimal.ROUND_HALF_UP);
        System.out.println(big3.doubleValue());

        System.out.println("FOOTPRINT_00519374792".getBytes());
    }
}
