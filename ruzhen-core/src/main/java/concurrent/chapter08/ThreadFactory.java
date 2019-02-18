package com.ruzhen.concurrent.chapter08;

public interface ThreadFactory {
    Thread createThread(Runnable runnable);
}
