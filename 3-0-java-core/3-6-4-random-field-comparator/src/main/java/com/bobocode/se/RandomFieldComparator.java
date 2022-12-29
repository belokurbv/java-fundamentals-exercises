package com.bobocode.se;

import com.bobocode.data.Accounts;
import com.bobocode.model.Account;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * A generic comparator that is comparing a random field of the given class. The field is either primitive or
 * {@link Comparable}. It is chosen during comparator instance creation and is used for all comparisons.
 * <p>
 * If no field is available to compare, the constructor throws {@link IllegalArgumentException}
 *
 * @param <T> the type of the objects that may be compared by this comparator
 *            <p><p>
 *            <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 *            <p>
 * @author Stanislav Zabramnyi
 */
public class RandomFieldComparator<T> implements Comparator<T> {
    private Field compared;

    private Class<T> clazz;

    public RandomFieldComparator(Class<T> targetType) {
        Objects.requireNonNull(targetType);
        this.compared = Arrays.stream(targetType.getDeclaredFields())
                .filter(field -> Comparable.class.isAssignableFrom(field.getType()))
                .findAny().orElseThrow(IllegalArgumentException::new);
        this.clazz = targetType;
    }

    /**
     * Compares two objects of the class T by the value of the field that was randomly chosen. It allows null values
     * for the fields, and it treats null value greater than a non-null value.
     *
     * @param o1
     * @param o2
     * @return positive int in case of first parameter {@param o1} is greater than second one {@param o2},
     * zero if objects are equals,
     * negative int in case of first parameter {@param o1} is less than second one {@param o2}.
     */
    @Override
    public int compare(T o1, T o2) {
        Objects.requireNonNull(o1);
        Objects.requireNonNull(o2);
        try {
            compared.setAccessible(true);
            Comparable f1 = (Comparable) compared.get(o1);
            Comparable f2 = (Comparable) compared.get(o2);
            return Comparator.<Comparable>nullsLast(Comparator.naturalOrder()).compare(f1, f2);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the name of the randomly-chosen comparing field.
     */
    public String getComparingFieldName() {
        return compared.getName();
    }

    /**
     * Returns a statement "Random field comparator of class '%s' is comparing '%s'" where the first param is the name
     * of the type T, and the second parameter is the comparing field name.
     *
     * @return a predefined statement
     */
    @Override
    public String toString() {
        return String.format("Random field comparator of class '%s' is comparing '%s'", this.clazz.getSimpleName(),
                compared.getName());
    }
}
