package com.bobocode.lesson_4.patterns.chain_of_responsibility;

public abstract class PayloadValidationChain {
    PayloadValidationChain next;

    public PayloadValidationChain() { }

    public PayloadValidationChain setNext(PayloadValidationChain next) {
        this.next = next;
        return this.next;
    }

    public boolean isValid(Payload payload) {
        if (next != null) {
            return validate(payload) && next.isValid(payload);
        } else {
            return validate(payload);
        }
    }

    public abstract boolean validate(Payload payload);
}
