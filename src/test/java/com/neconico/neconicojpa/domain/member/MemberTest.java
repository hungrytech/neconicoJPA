package com.neconico.neconicojpa.domain.member;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.mock;

public class MemberTest {

    private PasswordEncoder passwordEncoder;
    private final String defaultPassword = "1234";
    private final String defaultEncodePassword = "SFOEJF-FNFODSNOWDJ";

    @BeforeEach
    void setPasswordEncoder() {
        this.passwordEncoder = mock(PasswordEncoder.class);

        given(passwordEncoder.encode(defaultPassword))
                .willReturn(defaultEncodePassword);
    }


    @DisplayName("Member Entity생성")
    @Test
    void test_create_member_entity() throws Exception {
        Member member = getMember(passwordEncoder.encode(defaultPassword));

        assertThat(member.getPassword()).isEqualTo(defaultEncodePassword);
        assertThat(member.getGender()).isEqualTo(Gender.M);
    }

    @DisplayName("Member Etity변경")
    @Test
    void test_modify_member_entity() throws Exception {
        // given
        Member member = getMember(passwordEncoder.encode(defaultPassword));

        String modifyPassword = "4232";
        String modifyEncodePassword = "WFSFWF-WFFDF-DFFDF";

        given(passwordEncoder.encode(modifyPassword))
                .willReturn(modifyEncodePassword);

        int modifyZipCode = 1111;
        String modifyStreet = "경기도 수원시";

        // when
        member.modifyAddress(new Address(modifyZipCode, modifyStreet));
        member.modifyPassword(passwordEncoder.encode(modifyPassword));

        // then
        assertThat(member.getAddress().getStreet()).isEqualTo(modifyStreet);
        assertThat(member.getAddress().getZipCode()).isEqualTo(modifyZipCode);
        assertThat(member.getPassword()).isEqualTo(modifyEncodePassword);
    }



    private Member getMember(String encodePassword) {
        return Member.builder()
                .accountId("User1")
                .password(encodePassword)
                .name("user1")
                .gender(Gender.M)
                .email("user1@gmail.com")
                .phoneNumber("010-1111-1111")
                .address(new Address(1234, "서울시 도봉로"))
                .build();
    }


}
