package com.bobocode.lesson_4.patterns.chain_of_responsibility;

public class RegexValidation extends PayloadValidationChain {
    public final String regex;

    public RegexValidation(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean validate(Payload payload) {
        return payload.getValue().matches(regex);
    }
}
