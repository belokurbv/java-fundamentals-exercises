package com.bobocode.lesson_8;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SemaphoreSandbox {
    static int value;
    public static void main(String[] args) throws InterruptedException {
        var semaphore = new Semaphore(5);
        var pool = Executors.newFixedThreadPool(10);
        Runnable runnable = () -> {
            try {
                semaphore.acquire();
                for (int i = 0; i < 10; i++) {
                    value++;
                    System.out.println("Acquire " + Thread.currentThread().getName() + " " + value);
                    Thread.sleep(new Random().nextInt(1, 500));
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            semaphore.release();
        };

        for (int i = 0; i < 20; i++) {
            pool.submit(runnable);
        }

        pool.shutdown();

    }
}
