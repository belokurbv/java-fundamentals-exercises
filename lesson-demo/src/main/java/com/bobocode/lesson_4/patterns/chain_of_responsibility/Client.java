package com.bobocode.lesson_4.patterns.chain_of_responsibility;

public class Client {
    public static void main(String[] args) {
        var chain = new NotNullValidation();
        chain.setNext(new NotEmptyValidation()).setNext(new RegexValidation("[a-zA-Z]+"));

        System.out.println(chain.isValid(new Payload("Hello")));
        System.out.println(chain.isValid(new Payload("123")));
    }
}
