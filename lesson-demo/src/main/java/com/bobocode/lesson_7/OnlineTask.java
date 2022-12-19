package com.bobocode.lesson_7;

import java.util.Arrays;

public class OnlineTask {
    public static void main(String[] args) {
        int [] arr = new int[]{1, 10, 4, 2, 5};
        sort(arr);

        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        int[] left = new int[arr.length / 2];
        System.arraycopy(arr, 0, left, 0, arr.length / 2);

        int[] right = new int[arr.length - arr.length / 2];
        System.arraycopy(arr, left.length, right, 0, arr.length - arr.length / 2);
        sort(left);
        sort(right);
        merge(left, right, arr);
    }

    public static void merge(int[] left, int[] right, int[] arr) {
        int indexL = 0;
        int indexR = 0;
        for (int i = 0; i < arr.length; i++) {
            if (left.length == indexL) {
                arr[i] = right[indexR];
                indexR++;
            } else if (right.length == indexR) {
                arr[i] = left[indexL];
                indexL++;
            } else if (left[indexL] < right[indexR]){
                arr[i] = left[indexL];
                indexL++;
            } else if (left[indexL] > right[indexR]) {
                arr[i] = right[indexR];
                indexR++;
            }
        }
    }
}
