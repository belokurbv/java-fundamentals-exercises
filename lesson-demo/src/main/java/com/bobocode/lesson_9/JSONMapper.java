package com.bobocode.lesson_9;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class JSONMapper {
    public static void main(String[] args) throws IllegalAccessException {
        var json = toJSON(1L);
        System.out.println(json);
    }

    public static String getObjectValue(Object value) throws IllegalAccessException {
        if(value instanceof String) {
            return String.format("\"%s\"", value);
        } else if(value instanceof Number) {
            return value.toString();
        }
        return value.toString();
    }

    public static <T> String toJSON(T object) throws IllegalAccessException {
        var clazz = object.getClass();

        var objects =
                Arrays.stream(clazz.getFields()).map(
                field -> {
                    var sb = new StringBuilder();
                    sb.append(String.format("\"%s \": ", field.getName()));
                    try {
                        sb.append(String.format("\"%s \"", getObjectValue(field.get(object))));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    return sb.toString();
                }
        ).collect(Collectors.joining(", "));

        return "{" + objects + "}";
    }


}
