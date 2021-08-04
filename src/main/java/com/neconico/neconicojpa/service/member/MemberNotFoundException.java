package com.neconico.neconicojpa.service.member;

public class MemberNotFoundException extends RuntimeException{

    public MemberNotFoundException() {
    }

    @Override
    public String getMessage() {
        return "not found member";
    }
}
