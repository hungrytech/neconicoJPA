package com.neconico.neconicojpa.service.user;

import com.neconico.neconicojpa.domain.member.Member;
import com.neconico.neconicojpa.dto.member.MemberDto;
import com.neconico.neconicojpa.repository.member.MemberRepository;
import com.neconico.neconicojpa.service.member.MemberService;
import com.neconico.neconicojpa.service.member.MemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.NoSuchElementException;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    MemberService memberService;

    @Mock MemberRepository memberRepository;

    /*
    목 객체를 통한 행위 구현
     */
    @Mock ModelMapper modelMapper;
    @Mock Member member;

    /*
    생성자를 통해 연관관계 객체 주입
     */
    @BeforeEach
    void setMemberService() {
        memberService = new MemberServiceImpl(modelMapper, memberRepository);
    }

    @DisplayName("아이디 비번 검색 회원이 있을경우")
    @Test
    void test_find_member_id_pw() throws Exception {
        when(modelMapper.map(member, MemberDto.class)).thenReturn(new MemberDto());
        when(memberRepository.findMemberByIdAndPw(anyString(), anyString())).thenReturn(of(member));

        assertThat(memberService.findMemberByIdAndPassword("accountId", "accountPw"))
                .isInstanceOf(MemberDto.class);
    }

    @DisplayName("아이디 비번 검색 회원이 없을경우")
    @Test
    void test_not_find_member_id_pw() throws Exception {
        when(memberRepository.findMemberByIdAndPw(anyString(), anyString())).thenReturn(empty());

        assertThatThrownBy(() -> memberService.findMemberByIdAndPassword("any", "any"))
                .isInstanceOf(NoSuchElementException.class);
    }




}