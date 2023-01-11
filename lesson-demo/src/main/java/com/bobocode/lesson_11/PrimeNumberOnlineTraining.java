package com.bobocode.lesson_11;

import java.util.stream.IntStream;

public class PrimeNumberOnlineTraining {
    public static void main(String[] args) {
        var sum = IntStream.iterate(2, i -> i + 1)
                .filter(value -> IntStream.range(2, value / 2)
                        .noneMatch(number -> value % number == 0))
                .peek(System.out::println)
                .skip(500)
                .limit(20)
                .sum();
        System.out.println(sum);

    }
}
