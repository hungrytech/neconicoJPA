package com.neconico.neconicojpa.service.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
/*
Session login service 는 LoginService 를 구현하였고,
로그인, 로그아웃 시 세션에 등록하는 역할을 수행하고, 로그인 세션정보를 반환하는 역할을 수행한다.
 */
@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService{

    private static final String USER_ID = "USERID";

    private final HttpSession httpSession;

    @Override
    public void loginUser(String id) {
        httpSession.setAttribute(USER_ID, id);
    }

    @Override
    public void logoutUser(String id) {
        httpSession.invalidate();
    }

    @Override
    public String getCurrentLoginUser() {
        return (String) httpSession.getAttribute(USER_ID);
    }
}
