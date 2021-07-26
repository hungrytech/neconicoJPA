package com.neconico.neconicojpa.domain.member;

import com.neconico.neconicojpa.dto.member.MemberFormDto;
import com.neconico.neconicojpa.dto.member.ModifyMemberFormDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;


public class MemberTest {

    @DisplayName("member객체 생성 Test")
    @Test
    void createMemberTest() {
        // given
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setName("user1");
        memberFormDto.setAddress("서울시");
        memberFormDto.setZipCode(02423);

        // when
        Member member = Member.createMember(memberFormDto);

        // then
        assertThat(member.getName()).isEqualTo(memberFormDto.getName());
        assertThat(member.getAddress().getAddress()).isEqualTo(memberFormDto.getAddress());
        assertThat(member.getAddress().getZipCode()).isEqualTo(memberFormDto.getZipCode());

    }

    @DisplayName("member객체 정보변경")
    @Test
    void test() {
        // given
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setName("user1");
        memberFormDto.setAddress("서울시");
        memberFormDto.setZipCode(02423);

        Member member = Member.createMember(memberFormDto);

        ModifyMemberFormDto modifyMemberFormDto = new ModifyMemberFormDto();
        modifyMemberFormDto.setAddress("경기도");
        modifyMemberFormDto.setZipCode(25331);

        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        given(passwordEncoder.encode(modifyMemberFormDto.getPassword())).willReturn("UINEOFHG-FNOFJEJ-JHDFNO");

        // when
        member.modifyPassword(passwordEncoder
                .encode(modifyMemberFormDto.getPassword()));

        member.modifyAddress(new Address(modifyMemberFormDto.getZipCode(), modifyMemberFormDto.getAddress()));

        // then
        assertThat(member.getPassword()).isEqualTo("UINEOFHG-FNOFJEJ-JHDFNO");
    }



}
