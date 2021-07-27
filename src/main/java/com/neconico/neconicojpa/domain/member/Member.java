package com.neconico.neconicojpa.domain.member;

import com.neconico.neconicojpa.dto.member.MemberFormDto;
import lombok.*;

import javax.persistence.*;

import static org.springframework.util.StringUtils.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_pw", nullable = false)
    private String password;

    @Column(name = "member_name", nullable = false)
    private String name;

    @Column(nullable = false, length = 1)
    private String gender;

    @Column(nullable = false, length = 8)
    private String birthdate;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 13)
    private String phoneNumber;

    @Embedded
    private Address address;

    @Builder
    public Member(String password, String name, String gender, String birthdate, String email, String phoneNumber, Address address) {
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public static Member createMember(MemberFormDto memberFormDto) {
        return Member.builder()
                .password(memberFormDto.getPassword())
                .name(memberFormDto.getName())
                .gender(memberFormDto.getGender())
                .birthdate(memberFormDto.getBirthdate())
                .email(memberFormDto.getEmail())
                .phoneNumber(memberFormDto.getPhoneNumber())
                .address(new Address(memberFormDto.getZipCode(), memberFormDto.getStreet()))
                .build();

    }

    public void modifyPassword(String modifyPassword) {
        if(!hasText(modifyPassword)) {
            throw new IllegalArgumentException("not insert null or blank on modify password");
        }

        password = modifyPassword;
    }

    public void modifyAddress(Address modifyAddress) {
        if(modifyAddress == null
                || (!hasText(modifyAddress.getStreet()) || modifyAddress.getZipCode() == 0)) {
            throw new IllegalArgumentException("not insert null or blank on modify address");
        }

        address = modifyAddress;
    }
}
