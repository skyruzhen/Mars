package com.ruzhen.concurrent.chapter01;

public class TryConcurrency {
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                browseNews();
            }
        }.start();
        enjoyMusic();
    }

    private static void browseNews(){
        for(;;){
            System.out.println("Uh-hub, 好消息！");
            sleep(1);
        }
    }

    public static void enjoyMusic(){
        for(;;){
            System.out.println("Uh-hub, 好听！");
            sleep(1);
        }
    }

    private static void sleep(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
