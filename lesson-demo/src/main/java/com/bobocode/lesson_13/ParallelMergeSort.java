package com.bobocode.lesson_13;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class ParallelMergeSort {
    public static void main(String[] args) {
        var array = new int[] {10, 1, 0, 4, 3};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] array) {
        if (array.length < 2) {
            return;
        }

        var left = Arrays.copyOfRange(array, 0, array.length / 2);
        var right = Arrays.copyOfRange(array, array.length / 2, array.length);
        sort(left);
        sort(right);
        merge(array, left, right);
    }

    public static void merge(int[] array, int[] left, int[] right) {
        var idxL = 0;
        var idxR = 0;
        for (var i = 0; i < array.length; i++) {
            if (idxL >= left.length) {
                array[i] = right[idxR];
                idxR++;
            } else if(idxR >= right.length) {
                array[i] = left[idxL];
                idxL++;
            } else if (left[idxL] < right[idxR]) {
                array[i] = left[idxL];
                idxL++;
            } else if (left[idxL] > right[idxR]) {
                array[i] = right[idxR];
                idxR++;
            }
        }
    }
}
