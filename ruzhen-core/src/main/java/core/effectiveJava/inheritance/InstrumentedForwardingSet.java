package core.effectiveJava.inheritance;

import java.util.Collection;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/10/24
 * @since 1.0.0
 */
public class InstrumentedForwardingSet<E> extends ForwardingSet<E>{
    private int addCount = 0;
    public InstrumentedForwardingSet(Set<E> s) {
        super(s);
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