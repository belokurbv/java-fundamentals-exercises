package com.bobocode.lesson_4.patterns.factory_method;

import java.util.Calendar;

public class Client {
    public static void main(String[] args) {
        Connector connector = new NoSQLConnector();
        connector.connect();
    }
}
