package com.neconico.neconicojpa.domain.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static org.springframework.util.StringUtils.hasText;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String accountId;

    @Column(name = "account_pw", nullable = false)
    private String password;

    @Column(name = "member_name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false, length = 8)
    private String birthdate;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, length = 13)
    private String phoneNumber;

    @Embedded
    private Address address;

    @Builder
    public Member(String accountId, String password, String name, Gender gender,
                  String birthdate, String email, String phoneNumber, Address address) {

        this.accountId = accountId;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
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
