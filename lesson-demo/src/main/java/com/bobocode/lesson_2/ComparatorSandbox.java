package com.bobocode.lesson_2;

import java.util.Comparator;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComparatorSandbox {
    static class User {
        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        private Integer id;

        public InnerUserProperty getUserProperty() {
            return userProperty;
        }

        public void setUserProperty(InnerUserProperty userProperty) {
            this.userProperty = userProperty;
        }

        private InnerUserProperty userProperty;

        public User() {
            this.id = new Random().nextInt();
            this.userProperty = new InnerUserProperty(new Random().nextInt());
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    '}';
        }
    }

    static class UserProperty implements Comparable<UserProperty> {
        public UserProperty(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        private Integer id;


        @Override
        public int compareTo(UserProperty o) {
            return Integer.compare(o.id, this.id);
        }
    }

    static class InnerUserProperty extends UserProperty {
        public InnerUserProperty(Integer id) {
            super(id);
        }
    }


    static class Admin extends User {
    }


    public static void main(String[] args) {
        var lst = Stream.generate(Admin::new)
                .limit(10)
                .sorted(comparing2(User::getUserProperty))
                .collect(Collectors.toList());
        System.out.println(lst);
    }

    static <T, U extends Comparable<? super U>> Comparator<T> comparing(
            Function<? super T, ? extends U> fn) {
        return (o1, o2) -> fn.apply(o1).compareTo(fn.apply(o2));
    }

    static <T, U extends Comparable<U>> Comparator<T> comparing2(
            Function<T, U> fn
    ) {
        return (o1, o2) -> fn.apply(o1).compareTo(fn.apply(o2));
    }
}
