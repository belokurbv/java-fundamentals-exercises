package com.bobocode.lesson_7;

public class DeadlockSandbox {
    private static Object o1 = new Object();
    private static Object o2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        var thread1 = new Thread(() -> {
            synchronized (o1) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (o2) {
                    System.out.println("test");
                }
            }
        });

        var thread2 = new Thread(() -> {
            synchronized (o2) {
                synchronized (o1) {
                    System.out.println("test");
                }
            }
        });

        thread1.start();
        thread2.start();

    }
}
