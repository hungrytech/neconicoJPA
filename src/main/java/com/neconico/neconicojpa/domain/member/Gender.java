package com.neconico.neconicojpa.domain.member;

public enum Gender {
    MAN, WOMAN, UNKNOWN;

    Gender() {
    }

    public static Gender convertGender(String gender) {
        switch (gender) {
            case "man" :
                return Gender.MAN;
            case "woman" :
                return Gender.WOMAN;
            default:
                return Gender.UNKNOWN;
        }
    }
}
