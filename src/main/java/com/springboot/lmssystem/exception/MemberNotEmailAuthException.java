package com.springboot.lmssystem.exception;

public class MemberNotEmailAuthException extends RuntimeException {
    public MemberNotEmailAuthException(String error) {
        super(error);
    }
}
