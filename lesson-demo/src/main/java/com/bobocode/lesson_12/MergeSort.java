package com.bobocode.lesson_12;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {

    }

    void sort(int[] array) {
        if (array.length < 2) {
            return;
        }
        int[] left = Arrays.copyOfRange(array, 0, array.length / 2);
        int[] right = Arrays.copyOfRange(array,  array.length / 2, array.length);

        sort(left);
        sort(right);
        merge(left, right, array);
    }

    void merge(int[] left, int[] right, int[] array) {
        int idxL = 0;
        int idxR = 0;
        for (var i = 0; i < array.length; i++) {
            if (left.length <= idxL) {
                array[i] = right[idxR];
                idxR++;
            } else if (right.length <= idxR) {
                array[i] = right[idxL];
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
