package core.multiThreads.ServiceInvokerDemo;

import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lizhen
 * @create 2018/12/5
 * @since 1.0.0
 */
public final class Candidate implements Iterable<Endpoint>{
    //下游部件节点列表
    private final Set<Endpoint> endpoints;
    //下游部件节点的总权重
    public final int totalWeight;

    public Candidate(Set<Endpoint> endpoints) {
        int sum=0;
        for(Endpoint endpoint:endpoints){
            sum+=endpoint.weight;
        }
        totalWeight=sum;
        this.endpoints = endpoints;
    }

    @Override
    public Iterator<Endpoint> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Endpoint> action) {

    }

    @Override
    public Spliterator<Endpoint> spliterator() {
        return null;
    }

    public int getEndpointCount() {
        return totalWeight;
    }
}