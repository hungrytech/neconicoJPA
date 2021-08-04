package com.neconico.neconicojpa.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberJoinDto {
    private String accountId;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String gender;
    private int zipCode;
    private String street;
}
