package com.bobocode.lesson_8;

import java.util.Random;
import java.util.stream.IntStream;

public class OnlineTask {
    public static void main(String[] args) {
        var list = new Random().ints(1_000_000, 0, 1_000_000)
                .parallel()
                //.peek(System.out::println)
                .filter(value -> IntStream.range(2, value).noneMatch(number -> value % number == 0))
                .peek(value -> System.out.printf("%s %d \n", Thread.currentThread().getName(), value))
                .boxed().toList();
    }
}
