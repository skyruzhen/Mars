package com.ruzhen.concurrent.chapter05;

import java.util.concurrent.TimeUnit;

public class EventClient {
    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();
        new Thread(()->{
            for(;;){
                eventQueue.offer(new EventQueue.Event());
            }
        }, "Producer").start();

        for(int i = 0; i <100; i++)
        new Thread(()->{
            for(;;){
                eventQueue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer"+i).start();
    }
}
