package com.bobocode.lesson_2;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        var array = new int[] { 5, 2, 3, 1, 4};
        sort(array);
        System.out.println(Arrays.toString(array));
    }

    static void sort(int[] array) {
        for (var i = 1; i < array.length; i++) {
            var element = array[i];
            var j = i - 1;
            while (j >= 0 && element < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = element;
        }
    }
}
