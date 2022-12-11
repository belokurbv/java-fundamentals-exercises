package com.bobocode.lesson_4;

public class HeavyWeightProcessorProxy implements HeavyWeightProcessor {
    private HeavyWeightProcessor heavyWeightProcessor;

    @Override
    public void process() throws InterruptedException {
        if (heavyWeightProcessor == null) {
            heavyWeightProcessor = new HeavyWeightProcessorImpl();
        }
        heavyWeightProcessor.process();
    }

    public static void main(String[] args) throws InterruptedException {
        HeavyWeightProcessor processor = new HeavyWeightProcessorProxy();
        processor.process();
        processor.process();
        processor.process();
    }
}
