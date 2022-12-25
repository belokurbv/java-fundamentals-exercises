package com.bobocode.lesson_8;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class ParallelStreamHomework {
    public static final int MAX_SIZE = 100_000;

    static int[] generateArray() {
        return ThreadLocalRandom.current().ints(MAX_SIZE, 0, MAX_SIZE).toArray();
    }

    static long getSumSingleThread() {
        return Arrays.stream(generateArray())
                .filter(value -> IntStream.range(2, value)
                        .noneMatch(number -> value % number == 0))
                .sum();
    }

    static long getSumParallelThreads() {
        return Arrays.stream(generateArray())
                .parallel()
                .filter(value -> IntStream.range(2, value).noneMatch(number -> value % number == 0))
                .sum();
    }

    static long getSumParallelThreadsBoxed() {
        return Arrays.stream(generateArray())
                .parallel()
                .boxed()
                .filter(value -> IntStream.range(2, value).noneMatch(number -> value % number == 0))
                .reduce(0, Integer::sum);
    }

    static void calculate(Supplier<Long> supplier) {
        for (var i = 0; i < 10; i++) {
            var start = System.currentTimeMillis();
            var sum = supplier.get();
            var duration = System.currentTimeMillis() - start;
            System.out.printf("Sum: %d; Duration: %d ms \n", sum, duration);
        }
    }

    public static void main(String[] args) {
        System.out.println("Single thread");
        calculate(ParallelStreamHomework::getSumSingleThread);
        System.out.println("Parallel Threads");
        calculate(ParallelStreamHomework::getSumParallelThreads);
        System.out.println("Parallel Threads Boxed");
        calculate(ParallelStreamHomework::getSumParallelThreadsBoxed);
    }
}
