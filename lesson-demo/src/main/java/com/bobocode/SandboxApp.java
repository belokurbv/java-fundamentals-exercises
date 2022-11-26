package com.bobocode;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SandboxApp {
    static void recursiveCall() {
        recursiveCall();
    }

    static void dummyArray() {
        int[] array = new int[Integer.MAX_VALUE];
    }

    public static void main(String[] args) {
        //recursiveCall();
        int[] array = new int[Integer.MAX_VALUE];
    }

}
