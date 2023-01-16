package com.bobocode.lesson_11;

import java.util.List;

public class InsertionSortSandbox {
    public static void main(String[] args) {

    }

    static <T extends Comparable<? super T>> void sort(List<T> list) {
        for (var i = 1; i < list.size() ; i++) {
            var element = list.get(i);
            var j = i - 1;
            while (j >= 0 && list.get(j).compareTo(element) < 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, element);
        }
    }
}
