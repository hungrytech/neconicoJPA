package com.neconico.neconicojpa.controller;

import com.neconico.neconicojpa.dto.member.LoginFormDto;
import com.neconico.neconicojpa.dto.member.MemberDto;
import com.neconico.neconicojpa.dto.member.MemberJoinDto;
import com.neconico.neconicojpa.dto.member.MemberLoginDto;
import com.neconico.neconicojpa.service.login.LoginService;
import com.neconico.neconicojpa.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final LoginService loginService;

    @GetMapping("/login")
    public ResponseEntity<MemberLoginDto> login(@RequestBody LoginFormDto loginFormDto) {
        MemberLoginDto memberLoginDto = memberService.findMemberByAccountIdAndAccountPw(loginFormDto);

        loginService.loginMember(memberLoginDto.getAccountId());

        return ResponseEntity.ok(memberLoginDto);
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout() {
        loginService.logoutMember();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/members/new")
    public ResponseEntity<Void> insertMember(@RequestBody MemberJoinDto memberJoinDto) {
        boolean insertResult = memberService.insertMember(memberJoinDto);

        if(insertResult) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberDto> selectMember(@PathVariable long id) {
        MemberDto memberDto = memberService.findMemberById(id);

        return new ResponseEntity<>(memberDto, HttpStatus.OK);
    }

    @PatchMapping("/members/{id}/edit")
    public ResponseEntity<Void> updateMember(@RequestBody MemberDto memberDto, @PathVariable long id) {
        memberService.updateMember(memberDto, id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/members/{accountId}/exist")
    public ResponseEntity<Void> existAccountId(@PathVariable String accountId) {
        boolean exist = memberService.isExist(accountId);
        if(!exist) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
