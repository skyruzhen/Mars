package core.multiThreads.ch_5.case01;

import core.util.Tools;
import java.util.Random;

public class AlarmAgent {
    //单例
    private final static AlarmAgent INSTANCE = new AlarmAgent();

    //是否连接上告警服务器  默认false
    private boolean connectedToServer = false;

    //心跳线程， 用于检测告警代理与告警服务器的网络连接是否正常
    private final HeartbeatThread heartbeatThread = new HeartbeatThread();

    private AlarmAgent(){

    }

    public static AlarmAgent getInstance(){
        return INSTANCE;
    }

    public void init(){
        connectedToServer();
        heartbeatThread.setDaemon(true);
        heartbeatThread.start();
    }

    private void connectedToServer(){
        //创建并启动网络连接线程，在该线程中与告警服务器建立连接
        new Thread(){
            @Override
            public void run() {
                doConnect();
            }
        }.start();
    }

    private void doConnect() {
        //模拟实际操作耗时
        try {
            Tools.randomPause(100);
            synchronized (this){
                connectedToServer = true;
                //连接已建立完毕，通知以唤醒告警发送线程
                notify();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void sendAlarm(String message) throws InterruptedException {
        //通知前 先检测服务器的端口状态
        synchronized (this){
            while(!connectedToServer){
                System.out.println("Alarm agent was not conncetd to server.");
                wait();
            }
            //真正将警告消息报
            doSendAlarm(message);
        }
    }

    private void doSendAlarm(String message) {
        System.out.printf("Alarm sent: %s", message);
    }

    //心跳线程
    class HeartbeatThread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                while(true){
                    if(checkConnection()){
                        connectedToServer = true;
                    }else{
                        connectedToServer = false;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private boolean checkConnection() {
            boolean isConncetd = true;
            final Random random = new Random();
            int rand = random.nextInt(1000);
            if ( rand <= 500){
                isConncetd = false;
            }
            return isConncetd;
        }
    }

}
