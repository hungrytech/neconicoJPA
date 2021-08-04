package com.neconico.neconicojpa.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberLoginDto {
    private Long id;
    private String accountId;
    private String authority;
}
