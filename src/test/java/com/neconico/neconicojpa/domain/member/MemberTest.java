package com.neconico.neconicojpa.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;

public class MemberTest {

    @DisplayName("Member Entity생성")
    @Test
    void test_create_member_entity() throws Exception {
        // given
        PasswordEncoder passwordEncoder = getPasswordEncoder();

        // when
        Member member = getMember(passwordEncoder);

        // then
        assertThat(member.getPassword()).isEqualTo("SFOEJF-FNFODSNOWDJ");
        assertThat(member.getGender()).isEqualTo(Gender.M);
        assertThat(member.getAddress().getZipCode()).isEqualTo(23242);
        assertThat(member.getAddress().getStreet()).isEqualTo("서울시 강남로");
    }

    @Test
    void test_modify_member_entity() throws Exception {
        // given
        PasswordEncoder passwordEncoder = getPasswordEncoder();
        given(passwordEncoder.encode("4232")).willReturn("WFSFWF-WFFDF-DFFDF");

        Member member = getMember(passwordEncoder);

        // when
        member.modifyAddress(new Address(11111, "서울시 도봉로"));
        member.modifyPassword(passwordEncoder.encode("4232"));

        // then
        assertThat(member.getAddress().getStreet()).isEqualTo("서울시 도봉로");
        assertThat(member.getAddress().getZipCode()).isEqualTo(11111);
        assertThat(member.getPassword()).isNotEqualTo("SFOEJF-FNFODSNOWDJ");
    }

    private Member getMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .accountId("User1")
                .password(passwordEncoder.encode("1234"))
                .name("user1")
                .gender(Gender.M)
                .email("user1@gmail.com")
                .phoneNumber("010-1111-1111")
                .address(new Address(23242, "서울시 강남로"))
                .build();
    }

    private PasswordEncoder getPasswordEncoder() {
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        given(passwordEncoder.encode("1234")).willReturn("SFOEJF-FNFODSNOWDJ");

        return passwordEncoder;
    }


}
