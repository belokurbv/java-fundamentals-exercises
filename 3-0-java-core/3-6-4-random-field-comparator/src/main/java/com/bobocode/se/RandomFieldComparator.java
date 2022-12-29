package com.bobocode.se;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;
import com.bobocode.util.ExerciseNotCompletedException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * A generic comparator that is comparing a random field of the given class. The field is either primitive or
 * {@link Comparable}. It is chosen during comparator instance creation and is used for all comparisons.
 * <p>
 * If no field is available to compare, the constructor throws {@link IllegalArgumentException}
 *
 * @param <T> the type of the objects that may be compared by this comparator
 *<p><p>
 *  <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 *  <p>
 *
 * @author Stanislav Zabramnyi
 */
public class RandomFieldComparator<T> implements Comparator<T> {
    public static final Map<String, Class<?>> WRAPPERS_MAP = Map.of(
            "int", Integer.class,
            "long", Long.class,
            "float", Float.class,
            "char",  Character.class,
            "byte", Byte.class,
            "short", Short.class,
            "double", Double.class
    );

    private Field compared;

    public RandomFieldComparator(Class<T> targetType) {
        Objects.requireNonNull(targetType);
        var fields = Arrays.stream(targetType.getDeclaredFields())
                .peek(System.out::println)
                .filter(field -> Comparable.class.isAssignableFrom(field.getType()))
                .toList();
        if (fields.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.compared = fields.get(0);
    }

    String getRandomField(T object) {
        return object.getClass().getDeclaredFields()[0].getName();
    }

    /**
     * Compares two objects of the class T by the value of the field that was randomly chosen. It allows null values
     * for the fields, and it treats null value greater than a non-null value.
     *
     * @param o1
     * @param o2
     * @return positive int in case of first parameter {@param o1} is greater than second one {@param o2},
     *         zero if objects are equals,
     *         negative int in case of first parameter {@param o1} is less than second one {@param o2}.
     */
    @Override
    public int compare(T o1, T o2) {
        Objects.requireNonNull(o1);
        Objects.requireNonNull(o2);
        var field = getComparingFieldName();
        try {
            var field1= o1.getClass().getDeclaredField(field);
            var field2= o2.getClass().getDeclaredField(field);

            field1.setAccessible(true);
            field2.setAccessible(true);

            var f1 =  field1.get(o1);
            var f2 =  field2.get(o2);
            if (f1 == null && f2 == null) {
                return 0;
            } else if (f1 != null && f2 == null) {
                return 1;
            } else if (f2 != null && f1 == null) {
                return -1;
            }

            if (field1.getType().isPrimitive() && field2.getType().isPrimitive()) {
                var castClass = WRAPPERS_MAP.get(f1.getClass().getName());
                f1 = f1.getClass().cast(castClass);
                f2 = f2.getClass().cast(castClass);
            }

            return (int) f1.getClass().getDeclaredMethod("compareTo",
                            o1.getClass().getDeclaredField(field).getType())
                    .invoke(f1, f2);
        } catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        //throw new ExerciseNotCompletedException(); // todo: implement this method;
    }

    public static <U extends Comparable<? super U>> int compare(U o1, U o2) {
        return 0;
    }

    /**
     * Returns the name of the randomly-chosen comparing field.
     */
    public String getComparingFieldName() {
        return compared.getName();
       // return "balance";
    }

    /**
     * Returns a statement "Random field comparator of class '%s' is comparing '%s'" where the first param is the name
     * of the type T, and the second parameter is the comparing field name.
     *
     * @return a predefined statement
     */
    @Override
    public String toString() {
        throw new ExerciseNotCompletedException(); // todo: implement this method;
    }

    public static void main(String[] args) {
        RandomFieldComparator<Account> randomFieldComparator = new RandomFieldComparator<>(Account.class);
        var acc1 = Accounts.generateAccount();
        var acc2 = Accounts.generateAccount();
        System.out.println(acc1);
        System.out.println(acc2);
        System.out.println(randomFieldComparator.compare(acc1, acc2));
    }
}
