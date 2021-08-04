package com.neconico.neconicojpa.service.member;

import com.neconico.neconicojpa.dto.member.MemberDto;

import java.util.Optional;

public interface MemberService {

    MemberDto findMemberByIdAndPassword(String id, String pw);
}
