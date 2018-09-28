package core.CoreJava.GenericClass;

import core.CoreJava.proxyDemo.TraceHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.time.LocalDate;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/9/27
 * @since 1.0.0
 */
public class PairTest1 {
    public static void main(String[] args) {
        String[] words = {"Mary", "had", "a", "little", "lamb"};
   /*     for(int i = 0; i < words.length; i++){
            InvocationHandler handler = new TraceHandler(words[i]);
            Object proxy = Proxy.newProxyInstance(null, new Class[]{Comparable.class} ,handler);
            words[i] = proxy;
        }*/
/*        Pair<String, String, String> mm = ArrayAlg.minmax(words);
        System.out.println("min = "+mm.getFirst());
        System.out.println("max = "+mm.getSecond());*/
/*
        String middle = ArrayAlg.getMiddle(words);
        System.out.println(middle);*/

       /* Comparable test = ArrayAlg.getMiddle("hello", 1, null);  //找出共同的超类
        System.out.println(test);
        Integer i;*/

        //类型擦除存在泛型方法中
        //编译器在DateIntervel类中生成一个桥方法(bridge method) 解决擦除问题
        //虚拟机 用参数类型和返回类型确定一个方法
     /*   DateInterval interval = new DateInterval();
        Pair<LocalDate> pair = interval;
        pair.setFirst(LocalDate.MIN);
        pair.setSecond(LocalDate.now());

        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());*/

        Pair<String> pair = new Pair<>();
        System.out.println(pair.getClass());

        Pair<String> p = Pair.makePair(String::new);
        p.setFirst("ss");
        System.out.println(p.getFirst());

    }
}

class ArrayAlg {
/*
    public static Pair<String, String, String> minmax(String[] a){
        if(a == null || a.length == 0) return null;
        String min = a[0];
        String max = a[0];
        for(int i = 1; i < a.length; i++){
            if(min.compareTo(a[i]) > 0) min = a[i];
            if(max.compareTo(a[i]) < 0) max = a[i];
            System.out.println("当前："+a[i]+" min="+min+" max="+max);
        }
        return new Pair<>(min, max);
    }
*/

    public static <T> T getMiddle(T... a) {
        return a[a.length / 2];
    }

    public static <T extends Comparable> T min(T[] a) {
        T smallest = a[0];
        smallest.compareTo(a[1]);
        return smallest;
    }

}