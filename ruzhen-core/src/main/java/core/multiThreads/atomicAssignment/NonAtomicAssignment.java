package core.multiThreads.atomicAssignment;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈原子性〉
 *
 * @author lizhen
 * @create 2018/11/28
 * @since 1.0.0
 */
public class NonAtomicAssignment implements Runnable{

    static long value=0;
    private final long valueToSet;

    public NonAtomicAssignment(long valueToSet) {
        this.valueToSet = valueToSet;
    }

    public static void main(String[] args) {
        //TODO 更新data
        Thread updateThread1=new Thread(new NonAtomicAssignment(0L));
        Thread updateThread2=new Thread(new NonAtomicAssignment(-1L));
        PrintStream ps = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }
        });
        updateThread1.start();
        updateThread2.start();

        //共享变量value的快照（即瞬间值）
        long snapshot;
        while(0==(snapshot=value)||-1==snapshot){
            ps.print(snapshot);
            //只有32位系统能复现
            break;
        }
        System.err.printf("Unexpected data:%d(0x%016x)", snapshot,snapshot);
        ps.close();
        System.exit(0);
    }

    @Override
    public void run() {
        for(;;){
            value=valueToSet;
        }
    }
}