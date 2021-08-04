package com.neconico.neconicojpa.domain.member;

import com.neconico.neconicojpa.domain.item.Item;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @Column(nullable = false)
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

    @Column(nullable = false)
    private String authority;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();


    @Builder
    private Member(String accountId, String password, String name, Gender gender,
                   String birthdate, String email, String phoneNumber, Address address, String authority) {

        this.accountId = accountId;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.birthdate = birthdate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.authority = authority;
    }

    public void modifyPassword(String modifyPassword) {
        if(!hasText(modifyPassword)) {
            throw new IllegalArgumentException("not insert null or blank on modify password.");
        }

        password = modifyPassword;
    }

    public void modifyAddress(Address modifyAddress) {
        if(modifyAddress == null
                || (!hasText(modifyAddress.getStreet()) || modifyAddress.getZipCode() == 0)) {
            throw new IllegalArgumentException("not insert null or blank on modify address.");
        }

        address = modifyAddress;
    }

    public void modifyAuthority(String modifyAuthority) {
        if(!hasText(modifyAuthority)) {
            throw new IllegalArgumentException("not insert null or blank modify authority.");
        }

        this.authority = modifyAuthority;
    }
}
