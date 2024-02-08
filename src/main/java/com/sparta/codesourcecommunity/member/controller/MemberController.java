package com.sparta.codesourcecommunity.member.controller;

import com.sparta.codesourcecommunity.common.CommonResponseDto;
import com.sparta.codesourcecommunity.member.dto.MemberRequestDto;
import com.sparta.codesourcecommunity.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signup(@Valid @RequestBody MemberRequestDto memberRequestDto){
        try{
            memberService.signup(memberRequestDto);
        } catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().
                body(new CommonResponseDto("중복된 이메일 입니다.", HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.status(HttpStatus.CREATED.value())
            .body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }

}
