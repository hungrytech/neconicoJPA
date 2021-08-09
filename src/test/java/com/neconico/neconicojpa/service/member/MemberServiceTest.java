package com.neconico.neconicojpa.service.member;

import com.neconico.neconicojpa.domain.member.Address;
import com.neconico.neconicojpa.domain.member.Gender;
import com.neconico.neconicojpa.domain.member.Member;
import com.neconico.neconicojpa.dto.member.LoginFormDto;
import com.neconico.neconicojpa.dto.member.MemberDto;
import com.neconico.neconicojpa.dto.member.MemberJoinDto;
import com.neconico.neconicojpa.dto.member.MemberLoginDto;
import com.neconico.neconicojpa.repository.member.MemberRepository;
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
import static org.mockito.ArgumentMatchers.any;
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
    void test_find_member_by_accountId_and_accountPw() throws Exception {
        LoginFormDto loginFormDto = getLoginFormDto();

        when(modelMapper.map(member, MemberLoginDto.class)).thenReturn(new MemberLoginDto());
        when(memberRepository.findMemberByAccountIdAndAccountPw(anyString(), anyString())).thenReturn(of(member));

        assertThat(memberService.findMemberByAccountIdAndAccountPw(loginFormDto))
                .isInstanceOf(MemberLoginDto.class);
    }



    @DisplayName("아이디 비번 검색 회원이 없을경우")
    @Test
    void test_not_find_member_by_accountId_and_accountPw() throws Exception {
        LoginFormDto loginFormDto = getLoginFormDto();

        when(memberRepository.findMemberByAccountIdAndAccountPw(anyString(), anyString())).thenReturn(empty());

        assertThatThrownBy(() -> memberService.findMemberByAccountIdAndAccountPw(loginFormDto))
                .isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("회원 정보 가져오기")
    @Test
    void test_find_member_by_id() throws Exception {
        when(memberRepository.findById(any())).thenReturn(of(member));
        when(modelMapper.map(member, MemberDto.class)).thenReturn(new MemberDto());

        assertThat(memberService.findMemberById(1L)).isInstanceOf(MemberDto.class);
    }

    @DisplayName("회원 정보를 찾지 못할경우")
    @Test
    void test_not_find_member_by_id() throws Exception {
        when(memberRepository.findById(any())).thenReturn(empty());

        assertThatThrownBy(() -> memberService.findMemberById(1L))
                .isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("회원가입 성공")
    @Test
    void test_sign_up_success() throws Exception {
        MemberJoinDto memberJoinDto = getMemberJoinDto();

        when(memberRepository.findMemberByAccountId(anyString())).thenReturn(empty());

        when(memberRepository.save(any(Member.class))).thenReturn(member);

        assertThat(memberService.insertMember(memberJoinDto)).isTrue();
    }

    @DisplayName("회원가입 실패: 증복아이디")
    @Test
    void test_sign_up_fail() throws Exception {
        MemberJoinDto memberJoinDto = getMemberJoinDto();

        Member member = Member.builder()
                .accountId(memberJoinDto.getAccountId())
                .password(memberJoinDto.getPassword())
                .address(new Address(memberJoinDto.getZipCode(), memberJoinDto.getStreet()))
                .name(memberJoinDto.getName())
                .gender(Gender.convertGender(memberJoinDto.getGender()))
                .email(memberJoinDto.getEmail())
                .authority("ROLE_USER")
                .build();

        when(memberRepository.findMemberByAccountId(anyString())).thenReturn(of(member));

        assertThat(memberService.insertMember(memberJoinDto)).isFalse();
    }

    @DisplayName("아이디 증복 체크: 아이디 사용불가")
    @Test
    void test_exist_account_id() throws Exception {
        when(memberRepository.findMemberByAccountId(anyString())).thenReturn(of(member));

        assertThat(memberService.isExist("user1")).isTrue();
    }

    @DisplayName("아이디 증복 체크: 아이디 사용가능")
    @Test
    void test_not_exist_account_id() throws Exception {
        when(memberRepository.findMemberByAccountId(anyString())).thenReturn(empty());

        assertThat(memberService.isExist("user1")).isFalse();
    }

    private LoginFormDto getLoginFormDto() {
        LoginFormDto loginFormDto = new LoginFormDto();
        loginFormDto.setAccountId("accountId");
        loginFormDto.setPassword("password");
        return loginFormDto;
    }

    private MemberJoinDto getMemberJoinDto() {
        MemberJoinDto memberJoinDto = new MemberJoinDto();
        memberJoinDto.setAccountId("user1");
        memberJoinDto.setGender("man");
        memberJoinDto.setStreet("서울시");
        memberJoinDto.setZipCode(12342);
        return memberJoinDto;
    }

}