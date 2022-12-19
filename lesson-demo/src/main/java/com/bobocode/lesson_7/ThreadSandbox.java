package com.bobocode.lesson_7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class ThreadSandbox {
    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
        runnable.run();

        Thread thread = new Thread(runnable);
        thread.start();
        Callable<String> callable = () -> Thread.currentThread().getName();

        Executors.newSingleThreadExecutor().submit(runnable);
        Executors.newSingleThreadExecutor().submit(callable);
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(runnable, 1, 5, TimeUnit.SECONDS);
        Executors.newCachedThreadPool().submit(runnable);
        Executors.newFixedThreadPool(5).submit(runnable);
        Executors.newWorkStealingPool().submit(runnable);

        var list = List.of(1, 2, 3, 4);
        list.parallelStream().forEach(System.out::println);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.execute(runnable);

    }
}
