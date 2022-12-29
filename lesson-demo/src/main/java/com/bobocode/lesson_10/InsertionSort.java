package com.bobocode.lesson_10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InsertionSort {
    public static void main(String[] args) {
        var list = new ArrayList<Integer>();
        list.add(1);
        list.add(5);
        list.add(2);
        list.add(4);
        sort(list);
        System.out.println(list);
    }


    public static <T extends Comparable<? super T>> void sort(List<T> elements) {
        for (var i = 1; i < elements.size(); i++) {
            var element = elements.get(i);
            var j = i - 1;
            while (j >= 0 && element.compareTo(elements.get(j)) < 0) {
                elements.set(j + 1, elements.get(j));
                j--;
            }
            elements.set(j + 1, element);
        }
    }
}
