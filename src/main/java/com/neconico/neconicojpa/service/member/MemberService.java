package com.neconico.neconicojpa.service.member;

import com.neconico.neconicojpa.dto.member.LoginFormDto;
import com.neconico.neconicojpa.dto.member.MemberDto;
import com.neconico.neconicojpa.dto.member.MemberJoinDto;
import com.neconico.neconicojpa.dto.member.MemberLoginDto;

public interface MemberService {

    MemberLoginDto findMemberByAccountIdAndAccountPw(LoginFormDto loginFormDto);

    MemberDto findMemberById(long id);

    boolean insertMember(MemberJoinDto memberJoinDto);

    void updateMember(MemberDto memberDto, long id);

    boolean isExist(String accountId);
}
