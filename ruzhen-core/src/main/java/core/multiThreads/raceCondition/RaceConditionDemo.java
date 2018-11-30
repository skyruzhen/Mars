package core.multiThreads.raceCondition;

/**
 * 〈一句话功能简述〉<br>
 * 〈竞态〉
 *
 * @author lizhen
 * @create 2018/11/28
 * @since 1.0.0
 */
public class RaceConditionDemo {
    public static void main(String[] args) {
        Thread[] workerThreads=new Thread[1000];
        for(int i=0; i < 40; i++){
            workerThreads[i] = new WorkerThread(i, 10);
        }
        for(Thread ct:workerThreads){
            ct.start();
        }
    }

    static class WorkerThread extends Thread{
        private final int requestCount;
        public WorkerThread(int id, int requestCount) {
            super("worker-" + id);
            this.requestCount = requestCount;
        }

        @Override
        public void run() {
           int i=requestCount;
           String requestID;
            RequestIDGenerator requestIDGenerator = RequestIDGenerator.getInstance();
            while(i-- > 0){
                requestID = requestIDGenerator.nextID();

                //TODO处理请求
                processRequest(requestID);
            }
        }
        private void processRequest(String requestID){
            try {
                //模拟请求处理耗时
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s got requestID:%s %n", Thread.currentThread().getName(), requestID);
        }
    }

}