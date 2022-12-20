package com.bobocode.lesson_7;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class ForkJoinPoolMergeSandbox {
    public static void main(String[] args) {
        var pool = ForkJoinPool.commonPool();
        var array = new int[]{5, 3, 7, 1, 2, 0, 10, 4, -2, 12};
        pool.invoke(new MergeSortTask(array));
        System.out.println(Arrays.toString(array));
    }
}
