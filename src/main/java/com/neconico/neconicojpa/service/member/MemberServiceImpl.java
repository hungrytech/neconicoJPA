package com.neconico.neconicojpa.service.member;

import com.neconico.neconicojpa.domain.member.Address;
import com.neconico.neconicojpa.domain.member.Gender;
import com.neconico.neconicojpa.domain.member.Member;
import com.neconico.neconicojpa.dto.member.LoginFormDto;
import com.neconico.neconicojpa.dto.member.MemberDto;
import com.neconico.neconicojpa.dto.member.MemberJoinDto;
import com.neconico.neconicojpa.dto.member.MemberLoginDto;
import com.neconico.neconicojpa.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final ModelMapper modelMapper;
    private final MemberRepository memberRepository;

    @Override
    public MemberLoginDto findMemberByAccountIdAndAccountPw(LoginFormDto loginFormDto) {
        Optional<Member> findMember = memberRepository
                .findMemberByAccountIdAndAccountPw(loginFormDto.getAccountId(), loginFormDto.getPassword());

        return findMember.map(m -> modelMapper.map(m, MemberLoginDto.class))
                .orElseThrow();

    }

    @Override
    public MemberDto findMemberById(long id) {
        Optional<Member> findMember = memberRepository.findById(id);

        return findMember.map(f -> modelMapper.map(f, MemberDto.class))
                .orElseThrow();
    }

    @Transactional
    @Override
    public boolean insertMember(MemberJoinDto memberJoinDto) {
        Optional<Member> findMember = memberRepository.findMemberByAccountId(memberJoinDto.getAccountId());

        if(findMember.isEmpty()) {


            Member member = Member.builder()
                    .accountId(memberJoinDto.getAccountId())
                    .password(memberJoinDto.getPassword())
                    .address(new Address(memberJoinDto.getZipCode(), memberJoinDto.getStreet()))
                    .name(memberJoinDto.getName())
                    .gender(Gender.convertGender(memberJoinDto.getGender()))
                    .email(memberJoinDto.getEmail())
                    .phoneNumber(memberJoinDto.getPhoneNumber())
                    .authority("ROLE_USER")
                    .build();

            memberRepository.save(member);

            return true;
        }

        return false;
    }

    @Transactional
    @Override
    public void updateMember(MemberDto memberDto, long id) {
        Optional<Member> fineMember = memberRepository.findById(id);

        if(fineMember.isPresent()) {
            Member member = fineMember.get();
            member.modifyAddress(memberDto.getAddress());
            member.modifyPassword(memberDto.getPassword());
        }

    }


    @Override
    public boolean isExist(String accountId) {
        Optional<Member> findMember = memberRepository.findMemberByAccountId(accountId);

        return findMember.isPresent();
    }
}
