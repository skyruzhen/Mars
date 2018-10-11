package core.CoreJava.GenericClass;

import core.CoreJava.interfaceDemo.Employee;
import core.CoreJava.interfaceDemo.Manager;
import core.CoreJava.proxyDemo.TraceHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.function.IntFunction;
import java.util.function.Supplier;

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

        //不能创建参数化类型的数组
//        Pair<String>[] table = new Pair<String>[10]; //Error
        //
     /*   Pair<Employee>[] table = (Pair<Employee>[]) new Pair<?>[10];
        table[0] = new Pair<Employee>();
        String first = table[0].getFirst();*/

        //收集参数化类型对象
        ArrayList<Pair<String>> table = new ArrayList<Pair<String>>();

        //Varargs警告
        Collection<Pair<String>> table2 = new ArrayList<Pair<String>>();
        Pair<String> pair1 = new Pair<>();
        Pair<String> pair2 = new Pair<>();
        addAll(table2 ,pair1, pair2);

        //通过反射调用， 实例化类型变量
        Pair<String> pair3 = Pair.makePair(String.class);
        System.out.println("pair3.getFirst() = "+(pair3.getFirst()==null?"null":pair3.getFirst()));
        System.out.println("----");

        //数组构造器表达式 String::new 指示一个函数，给定所需的长度
        String[] ss = ArrayAlg.minmax(String[]::new ,"Tom", "Dick", "Harry");


    /*    Manager ceo = new Manager();
        Manager cfo = new Manager();
        Manager[] managerBuddies = {ceo, cfo};
        Employee[] employeesBuddies = managerBuddies;
        employeesBuddies[0] = new Employee();*/
    }

    //通配符  子类型   ? extends Employee
    public static void printBuddies(Pair<? extends Employee> p){

    }
    //通配符 超类
    public static void minmaxBouns(Manager[] a, Pair<? super Manager> result){

    }
    //通配符 无限定
    public static boolean hasNulls(Pair<?> p){
        return p.getFirst() == null || p.getSecond() == null;
    }

    //Varargs警告
    @SafeVarargs
    public static <T> void addAll(Collection<T> coll, T... ts){
        for(T t:ts){
            coll.add(t);
        }
    }

    //不能抛出或捕获泛型类的实例
    public static <T extends Throwable> void doWork(Class<T> t){
        /*   try {
            //do work
        } catch (T e) {
            e.printStackTrace();
        }*/
    }

    //在异常规范中使用类型变量是允许的
    public static <T extends Throwable> void doWork(T t) throws T{ //OK
        try {
            //
        } catch (Throwable realCause) {
            t.initCause(realCause);
            throw t;
        }
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

    public static <T extends Comparable> T[] minmax(IntFunction<T[]> constr,T...a){
//        Object[] mm = new Object[2];
        T[] mm = constr.apply(2);
        return  mm;
    }

}