package com.bobocode.lesson_7;

import java.util.Random;
import java.util.concurrent.Executors;

public class ThreadLocalSandbox {
    public static void main(String[] args) throws InterruptedException {
        var threadLocal = new ThreadLocal<Integer>();
        Runnable runnable = () -> {
            var value = new Random().nextInt();
            var name = Thread.currentThread().getName();
            System.out.println("Value for " + name + " " + value);
            threadLocal.set(value);
            System.out.println(String.format("Getting threadLocal + %s %s", name, threadLocal.get()));
        };
        var fixedThreadPool = Executors.newFixedThreadPool(5);
        for (var i = 1; i < 5; i++) {
            //Thread.sleep(new Random().nextInt());
            fixedThreadPool.submit(runnable);
        }
//        fixedThreadPool.
        fixedThreadPool.shutdown();
    }
}
