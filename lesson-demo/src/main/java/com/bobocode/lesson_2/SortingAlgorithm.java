package com.bobocode.lesson_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortingAlgorithm {
    public static void main(String[] args) {
        var array = new int[] { 5, 2, 4, 3, 1, 100};
       // insertionSort(array);
        var list = new ArrayList<Integer>();
        list.add(1);
        list.add(100);
        list.add(4);
        sort(list);
        System.out.println(list);
    }

    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        for(var i = 1; i < list.size(); i ++) {
            T element = list.get(i);
            var j = i - 1;
            while (j >= 0 && list.get(j).compareTo(element) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, element);
        }
    }

    public static void sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int element = array[i];
            for (int j = i - 1; j >= 0; j--) {
                if (element < array[j]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                } else {
                    break;
                }
            }
        }
    }

    public static void insertionSort(int[] array) {
        for (var i = 1; i < array.length; i++) {
            var current = array[i];
            var j = i - 1;
            while (j >= 0 && array[j] > current) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
    }
}
