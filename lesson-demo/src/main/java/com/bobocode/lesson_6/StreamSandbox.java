package com.bobocode.lesson_6;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamSandbox {
    public static void main(String[] args) {
//        var list = LongStream.iterate(2L, x -> x + 1)
//                .filter(value -> LongStream.iterate(2L, x -> x < value, x -> x + 1)
//                        .noneMatch(number -> value % number == 0))
//                .skip(500)
//                .limit(20)
//                .sum();
        var numberStack = List.of(1, 2, 3, 4, 5, 6, 7)
                .stream()
                .filter(x -> x <= 5)
                .collect(toStack());

        while (!numberStack.isEmpty()) {
            System.out.println(numberStack.pop());
        }

    }

    public static <T> Collector<T, Stack<T>, Stack<T>> toStack() {
        return Collector.of(
                Stack::new,
                Stack::push,
                (o1, o2) -> {
                    o2.addAll(o1);
                    return o2;
                }
        );
    }

//    public static Collector<? super Integer, Stack<Integer>, Stack<Integer>> toStack() {
//        return Collector.of(
//                Stack::new,
//                Stack::push,
//                (o1, o2) -> {
//                    o2.addAll(o1);
//                    return o2;
//                }
//        );
//    }
}
