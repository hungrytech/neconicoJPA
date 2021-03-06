package com.neconico.neconicojpa.dto.member;

import com.neconico.neconicojpa.domain.member.Address;
import com.neconico.neconicojpa.domain.member.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDto {

    private String accountId;

    private String password;

    private String name;

    private Gender gender;

    private String email;

    private String phoneNumber;

    private Address address;

    private String authority;
}
