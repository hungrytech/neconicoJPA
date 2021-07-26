package com.neconico.neconicojpa.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberFormDto {
    private String password;
    private String name;
    private String gender;
    private String birthdate;
    private String email;
    private String phoneNumber;
    private int zipCode;
    private String address;
}
