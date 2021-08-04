package com.neconico.neconicojpa.dto.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDto {
    private Long id;
    private String accountId;
    private String authority;
}
