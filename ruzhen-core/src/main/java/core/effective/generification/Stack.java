package core.effective.generification;

import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/10/24
 * @since 1.0.0
 */
public class Stack<T> {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    public Stack(){
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }
    public void push(T e){
        ensureCapacity();
        elements[size++] = e;
    }

    public T pop(){
        if(size ==0) throw new EmptyStackException();
        T result = (T)elements[--size];
        elements[size] = null;
        return result;
    }

    public void pushAll(Iterable<? extends T> src){
        for(T t:src){
            push(t);
        }
    }

    public void popAll(Collection<? super T> dst){
        while(!isEmpty())
            dst.add(pop());
    }


    public boolean isEmpty(){
        return size == 0;
    }

    private void ensureCapacity() {
        if(elements.length == size)
            elements = Arrays.copyOf(elements, 2*size +1);
    }
}