package com.neconico.neconicojpa.service.member;

import com.neconico.neconicojpa.domain.member.Member;
import com.neconico.neconicojpa.dto.member.MemberDto;
import com.neconico.neconicojpa.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;

    @Override
    public MemberDto findMemberByIdAndPassword(String id, String pw) {
        Optional<Member> findMember = memberRepository.findMemberByIdAndPw(id, pw);

        return findMember.map(m -> modelMapper.map(m, MemberDto.class))
                .orElseThrow();

    }
}
