package com.bobocode.lesson_5;


import java.util.Comparator;
import java.util.Random;
import java.util.function.DoubleConsumer;
import java.util.function.Function;
import java.util.function.ToLongBiFunction;

public class Util {
    /**
     * Returns a comparator of type T that is comparing values extracted using the provided mapper function.
     * <p>
     * E.g. imagine you need to compare accounts by their balance values.
     * <pre>{@code
     * Comparator<Account> balanceComparator = comparing(Account::getBalance);
     * }</pre>
     * <p>
     *
     * @param mapper a mapper function that allows to map an object to a comparable value
     * @return a comparator instance
     */
    public static <T, U extends Comparable<? super U>> Comparator<T> comparing(Function<? super T, ? extends U> fn) {
        return (c1, c2) -> fn.apply(c1).compareTo(fn.apply(c2));
    }

    public static void main(String[] args) {
        ToLongBiFunction<String, String> toLongBiFunctionStr = (s1, s2) -> Long.parseLong(s1) + Long.parseLong(s2);
        ToLongBiFunction<Integer, Integer> toLongBiFunction = (s1, s2) -> {
            return s1 + s2;
        };
        toLongBiFunctionStr.applyAsLong("1", "2");
        toLongBiFunction.applyAsLong(0, 2);
        var number = Double.valueOf(2d);

        var random = new Random();
        DoubleConsumer doubleConsumer = Math::abs;
        DoubleConsumer doubleConsumer1 = random::nextDouble;
    }
}
