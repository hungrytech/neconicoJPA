package com.neconico.neconicojpa.domain.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MemberTest {


    private String defaultPassword;

    @BeforeEach
    void setPasswordEncoder() {
        defaultPassword = "1234";
    }

    @DisplayName("member 정보 변경")
    @Test
    void test_modify_member_entity() throws Exception {
        // given
        Member member = getMember();

        String modifyPassword = "4232";


        int modifyZipCode = 1111;
        String modifyStreet = "경기도 수원시";

        // when
        member.modifyAddress(new Address(modifyZipCode, modifyStreet));
        member.modifyPassword(modifyPassword);

        // then
        assertThat(member.getAddress().getStreet()).isEqualTo(modifyStreet);
        assertThat(member.getAddress().getZipCode()).isEqualTo(modifyZipCode);
        assertThat(member.getPassword()).isEqualTo(modifyPassword);
    }

    @DisplayName("memeber 권한정보 변경")
    @Test
    void test_member_modify_authority() throws Exception {
        // given
        Member member = getMember();

        String modifyAuthority = "ROLE_ADMIN";

        // when
        member.modifyAuthority(modifyAuthority);

        // then
        assertThat(member.getAuthority()).isEqualTo(modifyAuthority);
    }

    @DisplayName("비밀번호 변경 시 잘못된값 주입")
    @Test
    void test_modify_password_exception() throws Exception {

        Member member = getMember();

        String modifyPassword = "";

        assertThatThrownBy(() -> member.modifyPassword(modifyPassword))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("not insert null or blank on modify password.");
    }

    @DisplayName("주소 변경 시 잘못된 값 주입")
    @Test
    void test_modify_address_exception() throws Exception {
        Member member = getMember();

        Address modifyAddress = new Address(0, "");

        assertThatThrownBy(() -> member.modifyAddress(modifyAddress))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("not insert null or blank on modify address.");
    }

    @DisplayName("권한 변경 시 잘못된 값 주입")
    @Test
    void test_modify_authority_exception() throws Exception {
        String modifyAuthority = null;

        Member member = getMember();

        assertThatThrownBy(() -> member.modifyAuthority(modifyAuthority))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("not insert null or blank modify authority.");
    }




    private Member getMember() {
        return Member.builder()
                .accountId("User1")
                .password(defaultPassword)
                .name("user1")
                .gender(Gender.M)
                .email("user1@gmail.com")
                .phoneNumber("010-1111-1111")
                .address(new Address(1234, "서울시 도봉로"))
                .authority("ROLE_USER")
                .build();
    }


}
