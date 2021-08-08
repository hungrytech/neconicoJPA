# Neconico(내꼬니꼬)
### 기존 팀프로젝트를 사용하여 Restful API로 재구성
- - -

## 프로젝트 목적
기존에 진행한 프로젝트를 전체적으로 리펙토링하고 기존의 Thymeleaf를 사용한 SSR(Server Side Rendering) 
방식이 아닌 Restful API로서의 역할을 할 수 있도록 구성 (서버에서 뷰를 구성하지 않음)
 * Mybatis -> JPA 변경 (도메인 재설계)
 * Thymeleaf -> Restful API 변경

## 사용기술
 * Spring boot
 * Spring Security
 * Gradle
 * Spring Data JPA
 * Mysql
 * Java11
 * JWT  

## 주요기능
회원
 * 회원 가입
 * 로그인 및 로그아웃
 * 개인정보 수정
 * 구매요청   
상품
 * 등록 및 수정
 * 거래 요청
 * 요청에 따른 상태변화
 * 검색


## 기술적인 집중 요소
 * TDD방식을 따르는 개발
 * 객체지향의 설계 협력, 책임, 메세지를 생각하며 의미있는 코드 작성
 
## API 명세
현재 프로젝트 진행중이므로 업데이트될 예정입니다 :)
