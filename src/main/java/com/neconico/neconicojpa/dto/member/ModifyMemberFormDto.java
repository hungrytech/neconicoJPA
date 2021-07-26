package com.neconico.neconicojpa.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ModifyMemberFormDto {
    private String password;
    private int zipCode;
    private String address;
}
