package com.bobocode.lesson_2;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        var array = new int[] { 5, 2, 4, 3, 1, 100};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    static void sort(int[] array) {
        var mid = array.length / 2;
        if (array.length < 2) {
            return;
        }
        int[] left = new int[mid];
        int[] right = new int[array.length - mid];

        for (var i = 0; i < mid; i++) {
            left[i] = array[i];
        }

        for (var i = mid; i < array.length; i++) {
            right[i - mid] = array[i];
        }

        sort(left);
        sort(right);

        merge(array, left, right);
    }

    static void merge(int[] result, int[] left, int[] right) {
        int idxL = 0;
        int idxR = 0;
        for (var i = 0; i < result.length; i++) {
            if (idxL == left.length) {
                result[i] = right[idxR];
                idxR++;
            } else if (idxR == right.length) {
                result[i] = left[idxL];
                idxL++;
            } else if (left[idxL] < right[idxR]) {
                result[i] = left[idxL];
                idxL++;
            } else  {
                result[i] = right[idxR];
                idxR++;
            }
        }
    }
}
