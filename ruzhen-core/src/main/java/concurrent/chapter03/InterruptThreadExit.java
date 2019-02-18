package com.ruzhen.concurrent.chapter03;

import java.util.concurrent.TimeUnit;

public class InterruptThreadExit {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(){
            @Override
            public void run() {
                System.out.println("I will start work");
                while(!isInterrupted()){
                    //working
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Thread.currentThread().interrupt();
                    System.out.println("no response in 5s");
                }
                System.out.println("I will be exiting.");
            }
        };
        t.start();
        TimeUnit.MINUTES.sleep(1);
        System.out.println("System will be shutdonw.");
        t.interrupt();
    }
}
