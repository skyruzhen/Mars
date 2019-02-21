package core.effectiveJava.inheritance;
import java.util.Collection;
import java.util.HashSet;

/**
 * 〈一句话功能简述〉<br>
 * 〈错误的继承〉
 *
 * @author lizhen
 * @create 2018/10/24
 * @since 1.0.0
 */
public class InstrumentedHashSet<E> extends HashSet<E> {
    private int addCount = 0;
    public InstrumentedHashSet(){
    }
    public InstrumentedHashSet(int initCap, float loadFactor){
        super(initCap, loadFactor);
    }
    @Override public boolean add(E e){
        addCount ++;
        return super.add(e);
    }

    @Override public boolean addAll(Collection<? extends  E> c){
        addCount += c.size();
        return super.addAll(c);
    }
    public int getAddCount(){
        return addCount;
    }
}