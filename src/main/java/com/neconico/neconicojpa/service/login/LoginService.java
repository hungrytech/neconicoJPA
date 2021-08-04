package com.neconico.neconicojpa.service.login;

/*
로그인 역할을 위해 만든 서비스로 인터페이스를 통해
컨틀러가 어떤 로그인을 이용하는지 알 수 없도록 했다.
또한 다른 로그인 방식이 추가 될경우 해당 로그인 서비스를 구현하면 되도록 인터페이스를 이용했다.
 */
public interface LoginService {

    void loginMember(String id);

    void logoutMember(String id);

    String getCurrentLoginMember();

}
