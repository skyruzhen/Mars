package core.CoreJava.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

/**
 * 〈一句话功能简述〉<br>
 * 〈fork-join框架〉
 *
 * @author lizhen
 * @create 2018/10/18
 * @since 1.0.0
 */
public class ForkJoinTest {
    public static void main(String[] args) {
        final int SIZE = 10000000;
        double[] numbers = new double[SIZE];
        for(int i = 0; i < SIZE; i++) numbers[i] = Math.random();
        Counter counter = new Counter(numbers, 0, numbers.length, x->x>0.5);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(counter);
        System.out.println(counter.join());
    }
}

class Counter extends RecursiveTask<Integer>{
    public static final int THRESHOLD = 1000;
    private double[] values;
    private int from;
    private int to;
    private DoublePredicate filter;

    public Counter(double[] values, int from, int to, DoublePredicate filter){
        this.values = values;
        this.from = from;
        this.to = to;
        this.filter = filter;
    }

    @Override
    protected Integer compute() {
        if(to - from < THRESHOLD){ // 大于 THRESHOLD的计算量时，才使用Fork-Join
            int count = 0;
            for(int i = from; i < to; i++){
                if(filter.test(values[i])) count++;  //判断
            }
            return count;   //返回每个计算的统计结果
        }else{
            //递归调用
            int mid = (from + to)/2;
            Counter first = new Counter(values, from, mid, filter);
            Counter second = new Counter(values, mid, to ,filter);
            invokeAll(first, second);  //阻塞所有任务
            return first.join()+second.join();  //返回所有计算结果(调用compute方法)的和
        }

    }
}