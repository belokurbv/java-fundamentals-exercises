package com.bobocode.lesson_7;

import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class MergeSortTask extends RecursiveAction {
    private int[] array;

    public MergeSortTask(int[] array) {
        this.array = array;
    }

    @Override
    protected void compute() {
        System.out.printf("Invoke %s%n", Thread.currentThread().getName());
        if (array.length < 2) {
            return;
        }
        var left = Arrays.copyOfRange(array, 0, array.length / 2);
        var right = Arrays.copyOfRange(array,  array.length / 2, array.length);
        var leftTask = new MergeSortTask(left);
        var rightTask = new MergeSortTask(right);
        leftTask.fork();
        rightTask.compute();
        leftTask.join();
        merge(left, right, array);
    }

    private void merge(int[] left, int[] right, int[] array) {
        var leftIdx = 0;
        var rightIdx = 0;
        for (var i = 0; i < array.length; i++) {
            if (leftIdx == left.length) {
                array[i] = right[rightIdx];
                rightIdx++;
            } else if (rightIdx == right.length) {
                array[i] = left[leftIdx];
                leftIdx++;
            } else if (left[leftIdx] < right[rightIdx]) {
                array[i] = left[leftIdx];
                leftIdx++;
            } else if (left[leftIdx] > right[rightIdx]) {
                array[i] = right[rightIdx];
                rightIdx++;
            }
        }
    }
}
