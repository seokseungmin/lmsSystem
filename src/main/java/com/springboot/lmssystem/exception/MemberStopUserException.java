package com.springboot.lmssystem.exception;

public class MemberStopUserException extends RuntimeException{
    public MemberStopUserException(String error){
        super(error);
    }
}
