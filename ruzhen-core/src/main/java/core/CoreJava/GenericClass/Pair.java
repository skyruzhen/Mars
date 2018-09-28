package core.CoreJava.GenericClass;

import java.util.function.Supplier;

/**
 * 〈一句话功能简述〉<br>
 * 〈泛型类〉
 *
 * @author lizhen
 * @create 2018/9/27
 * @since 1.0.0
 */
public class Pair<T> {
    //泛型类可看作普通类的工厂
    private T first;
    private T second;
    private T unused;
    public Pair(){
        first = null;
        second = null;
    }

    public Pair(T first, T second){
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }

    public static <T> Pair<T> makePair(Supplier<T> constr){
        return new Pair<>(constr.get(), constr.get());
    }
}