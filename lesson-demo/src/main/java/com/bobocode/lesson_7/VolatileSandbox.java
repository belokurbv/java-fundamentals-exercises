package com.bobocode.lesson_7;

public class VolatileSandbox {
    // If this variable is not volatile, program never terminates
    private static volatile boolean timeToStop = false;
    private static long count = 0;

    public static void main(String[] argsIgnored) throws InterruptedException {
        Thread busyThread = new Thread(() -> {
            while (! timeToStop) {
                count += 1;
            }
        });
        busyThread.start();
        Thread.sleep(1000);
        timeToStop = true;
       // busyThread.join();
        System.out.println(count);
    }
}
