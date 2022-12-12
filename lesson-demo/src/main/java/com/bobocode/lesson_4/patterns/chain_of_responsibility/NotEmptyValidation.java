package com.bobocode.lesson_4.patterns.chain_of_responsibility;

public class NotEmptyValidation extends PayloadValidationChain {
    @Override
    public boolean validate(Payload payload) {
        return payload.getValue() != null && !payload.getValue().isEmpty();
    }
}
