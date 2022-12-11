package com.bobocode.lesson_4;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 4, 0, 6, 2, 10};
        sort(array);
        System.out.println(Arrays.toString(array));

    }

    public static void sort(int... array) {
        if (array.length < 2) {
            return;
        }

        int[] left = new int[array.length / 2];
        int[] right = new int[array.length  - array.length / 2];

        System.arraycopy(array, 0, left, 0, array.length / 2);
        System.arraycopy(array, (array.length / 2) , right, 0, array.length  - (array.length / 2));
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));
        sort(left);
        sort(right);
        merge(left, right, array);
    }

    public static void merge(int[] left, int[] right, int[] result) {
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
