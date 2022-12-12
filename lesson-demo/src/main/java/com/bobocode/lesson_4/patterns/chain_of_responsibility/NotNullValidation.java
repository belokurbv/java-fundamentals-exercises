package com.bobocode.lesson_4.patterns.chain_of_responsibility;

public class NotNullValidation extends PayloadValidationChain {

    @Override
    public boolean validate(Payload payload) {
        return payload != null;
    }
}
