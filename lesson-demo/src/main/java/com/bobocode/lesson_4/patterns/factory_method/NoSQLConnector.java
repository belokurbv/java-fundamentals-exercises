package com.bobocode.lesson_4.patterns.factory_method;

public class NoSQLConnector implements Connector {
    @Override
    public void connect() {
        System.out.println("NOSQL Connector connected");
    }
}
