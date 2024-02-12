package com.sparta.codesourcecommunity.member.controller;

import com.sparta.codesourcecommunity.common.CommonResponseDto;
import com.sparta.codesourcecommunity.member.dto.EmailCheckDto;
import com.sparta.codesourcecommunity.member.dto.MemberLoginRequestDto;
import com.sparta.codesourcecommunity.member.dto.MemberRequestDto;
import com.sparta.codesourcecommunity.member.dto.MemberResponseDto;
import com.sparta.codesourcecommunity.member.dto.ModifyPasswordDto;
import com.sparta.codesourcecommunity.member.service.EmailService;
import com.sparta.codesourcecommunity.member.service.MemberService;
import com.sparta.codesourcecommunity.security.JwtUtil;
import com.sparta.codesourcecommunity.security.MemberDetailsImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final EmailService emailService;
    private final JwtUtil jwtUtil;


    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signup(
        @Valid @RequestBody MemberRequestDto memberRequestDto) {
        memberService.signup(memberRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED.value())
            .body(new CommonResponseDto("이메일 인증을 완료하세요.", HttpStatus.CREATED.value()));
    }

    @PostMapping("/mailAuthCheck")
    public ResponseEntity<CommonResponseDto> AuthCheck(
        @Valid @RequestBody EmailCheckDto emailCheckDto) {
        if (emailService.CheckAuthNum(emailCheckDto.getEmail(), emailCheckDto.getAuthNum())) {
            memberService.signupComplete(emailCheckDto.getEmail());
            emailService.delete(emailCheckDto.getEmail());
            memberService.delete(emailCheckDto.getEmail());
            return ResponseEntity.ok()
                .body(new CommonResponseDto("회원가입 완료", HttpStatus.OK.value()));
        } else {
            emailService.delete(emailCheckDto.getEmail());
            memberService.delete(emailCheckDto.getEmail());
            return ResponseEntity.badRequest()
                .body(new CommonResponseDto("올바르지 않은 인증번호", HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<CommonResponseDto> login(
        @Valid @RequestBody MemberLoginRequestDto memberLoginRequestDto,
        HttpServletResponse response) {
        memberService.login(memberLoginRequestDto);
        // Response Header에 Key : JwtUtil.AUTHORIZATION_HEADER , Value : jwtUtil.createToken(memberRequestDto.getEmail()) 셋팅
        String token = jwtUtil.createToken(memberLoginRequestDto.getEmail());
        response.setHeader(JwtUtil.AUTHORIZATION_HEADER,
            token);
        // cookie에 token 담기
        jwtUtil.addJwtToCookie(token, response);
        return ResponseEntity.ok().body(new CommonResponseDto("로그인 성공", HttpStatus.OK.value()));
    }

    @GetMapping("/profile")
    public MemberResponseDto getProfile(@AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        return memberService.getProfile(memberDetails);
    }

    @PatchMapping("/nickname")
    public ResponseEntity<CommonResponseDto> modifyNickname(
        @AuthenticationPrincipal MemberDetailsImpl memberDetails,
        @Valid @RequestBody MemberRequestDto memberRequestDto) {
        memberService.modifyNickname(memberRequestDto, memberDetails.getMember());
        return ResponseEntity.ok().body(new CommonResponseDto("닉네임 수정 성공", HttpStatus.OK.value()));
    }

    @PatchMapping("/password")
    public ResponseEntity<CommonResponseDto> modifyPassword(
        @AuthenticationPrincipal MemberDetailsImpl memberDetails,
        @Valid @RequestBody ModifyPasswordDto passwordDto) {
        memberService.modifyPassword(passwordDto, memberDetails.getMember());
        return ResponseEntity.ok().body(new CommonResponseDto("비밀번호 수정 성공", HttpStatus.OK.value()));
    }

    @PatchMapping("/introduce")
    public ResponseEntity<CommonResponseDto> modifyIntroduce(
        @AuthenticationPrincipal MemberDetailsImpl memberDetails,
        @Valid @RequestBody MemberRequestDto memberRequestDto) {
        memberService.modifyIntroduce(memberRequestDto, memberDetails.getMember());
        return ResponseEntity.ok().body(new CommonResponseDto("자기소개 수정 성공", HttpStatus.OK.value()));
    }
}
