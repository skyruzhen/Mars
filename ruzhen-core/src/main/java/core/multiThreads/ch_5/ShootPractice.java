package core.multiThreads.ch_5;

import core.util.Tools;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ShootPractice {
    //参与打靶训练的全部士兵
    final Soldier[][] rank;

    //靶的个数， 即每排中士兵的个数
    final int N;

    //打靶持续时间（单位：秒）
    final int lasting;

    //标识是否继续打靶
    volatile boolean done = false;

    //用来指示进行下一轮打靶的是哪一排的士兵
    volatile int nextLine = 0;
    final CyclicBarrier shiftBarrier;
    final CyclicBarrier startBarrier;

    public ShootPractice(final int lineCount, int n, int lasting) {
        N = n;
        this.rank = new Soldier[lineCount][N];
        this.lasting = lasting;
        for( int i = 0; i < lineCount; i++){
            for(int j = 0; j < N; j++){
                rank[i][j] = new Soldier(i*N + j);
            }
        }

        shiftBarrier = new CyclicBarrier(N, ()->{
            //更新下一轮打靶的排
            nextLine = (nextLine + 1) % lineCount;
            System.out.println("Next turn is :"+ nextLine);
        });

        startBarrier = new CyclicBarrier(N);
    }

    public static void main(String[] args) throws InterruptedException {
        ShootPractice shootPractice = new ShootPractice(5, 4, 24);
        shootPractice.start();
    }

    public void start() throws InterruptedException {
        //创建并启动工作者线程
        Thread[] threads = new Thread[N];
        for(int i= 0; i < N; i++){
            threads[i] = new Shooting(i);
            threads[i].start();
        }

        //指定时间后停止打靶
        Thread.sleep(lasting*1000);
        stop();
        for(Thread t: threads){
            t.join();
        }
        System.out.println("Practice finished.");
    }

    public void stop(){
        done = true;
    }

    class Shooting extends Thread{
        final int index;

        public Shooting(int index) {
            this.index = index;
        }

        @Override
        public void run() {
           Soldier soldier;
           try {
               while (!done) {
                   soldier = rank[nextLine][index];
                   //一排中的士兵必须同时开始射击
                   startBarrier.await();
                   //该士兵开始射击
                   soldier.fire();
                   //一排中的士兵必须等待该排中的所有其他士兵射击完毕才能够离开射击点
                   shiftBarrier.await();
               }
           } catch (InterruptedException e) {
               e.printStackTrace();
           } catch (BrokenBarrierException e) {
               e.printStackTrace();
           }
        }//run方法结束
    }//类Shooting定义结束

    //参与打靶训练的士兵
    static class Soldier{
        //打靶号
        private final int seqNo;

        //打靶成绩
        private ArrayList<Integer> results;

        public Soldier(int seqNo){
            this.seqNo = seqNo;
            results = new ArrayList<>();
        }

        public void fire(){
            System.out.println(this+" start firing...");
            Tools.randomPause(5000);
            System.out.println(this+" fired.");
            int rs = new Random().nextInt(10);
            results.add(rs);
            System.out.println("本次射击成绩："+rs);
        }

        @Override
        public String toString() {
            return "Soldier{" +
                    "seqNo=" + seqNo +
                    '}';
        }
    }//类Soldier定义结束

}
