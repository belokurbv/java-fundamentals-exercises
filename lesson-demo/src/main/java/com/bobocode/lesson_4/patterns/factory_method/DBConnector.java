package com.bobocode.lesson_4.patterns.factory_method;

public class DBConnector implements Connector {
    @Override
    public void connect() {
        System.out.println("DB Connector connected!");
    }
}
