package com.bobocode.lesson_4;

public class HeavyWeightProcessorImpl implements HeavyWeightProcessor {

    public HeavyWeightProcessorImpl() throws InterruptedException {
        System.out.println("Init...");
        Thread.sleep(1000);
        System.out.println("Init completed!");
    }

    @Override
    public void process() {
        System.out.println("Process method calling");
    }
}
