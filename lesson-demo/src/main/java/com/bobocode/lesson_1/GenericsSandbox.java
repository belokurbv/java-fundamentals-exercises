package com.bobocode.lesson_1;

import java.util.List;

public class GenericsSandbox {
    public static void main(String[] args) {

    }

    static <T> void copy(List<? extends T> from, List<? super T> to) {
        for (var element :
                from) {
            to.add(element);
        }
    }
}
