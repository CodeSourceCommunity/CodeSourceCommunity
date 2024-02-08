package com.sparta.codesourcecommunity.member.controller;

import com.sparta.codesourcecommunity.common.CommonResponseDto;
import com.sparta.codesourcecommunity.member.dto.MemberRequestDto;
import com.sparta.codesourcecommunity.member.dto.ModifyPasswordDto;
import com.sparta.codesourcecommunity.member.exception.DuplicateMemberException;
import com.sparta.codesourcecommunity.member.exception.NotMatchPasswordException;
import com.sparta.codesourcecommunity.member.service.MemberService;
import com.sparta.codesourcecommunity.security.JwtUtil;
import com.sparta.codesourcecommunity.security.MemberDetailsImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signup(
        @Valid @RequestBody MemberRequestDto memberRequestDto) {
        try {
            memberService.signup(memberRequestDto);
        } catch (DuplicateMemberException e) {
            return ResponseEntity.badRequest().
                body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.status(HttpStatus.CREATED.value())
            .body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponseDto> login(@RequestBody MemberRequestDto memberRequestDto,
        HttpServletResponse response) {
        try {
            memberService.login(memberRequestDto);
        } catch (NotMatchPasswordException e) {
            return ResponseEntity.badRequest()
                .body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }

        // Response Header에 Key : JwtUtil.AUTHORIZATION_HEADER , Value : jwtUtil.createToken(memberRequestDto.getEmail()) 셋팅
        response.setHeader(JwtUtil.AUTHORIZATION_HEADER,
            jwtUtil.createToken(memberRequestDto.getEmail()));
        return ResponseEntity.ok().body(new CommonResponseDto("로그인 성공", HttpStatus.OK.value()));
    }

    @PatchMapping("/nickname")
    public ResponseEntity<CommonResponseDto> modifyNickname(@AuthenticationPrincipal
    MemberDetailsImpl memberDetails, @Valid @RequestBody MemberRequestDto memberRequestDto) {
        try {
            memberService.modifyNickname(memberRequestDto, memberDetails.getMember());
        } catch (NotMatchPasswordException e) {
            return ResponseEntity.badRequest()
                .body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.ok().body(new CommonResponseDto("닉네임 수정 성공", HttpStatus.OK.value()));
    }

    @PatchMapping("/password")
    public ResponseEntity<CommonResponseDto> modifyPassword(@AuthenticationPrincipal
    MemberDetailsImpl memberDetails, @Valid @RequestBody ModifyPasswordDto passwordDto) {
        try {
            memberService.modifyPassword(passwordDto, memberDetails.getMember());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                .body(new CommonResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }

        return ResponseEntity.ok().body(new CommonResponseDto("비밀번호 수정 성공", HttpStatus.OK.value()));
    }
}
