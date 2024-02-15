package com.sparta.codesourcecommunity.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class MemberRequestDto {

    @Email(message = "email 형식으로 입력하세요.")
    private String email;
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-zA-Z0-9]+$", message = "닉네임을 한글, 알파벳 대,소문자, 숫자로만 입력하세요.")
    private String nickname;
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}+$", message = "비밀번호를 알파벳 대,소문자, 숫자로만 구성되며, 8자 이상 15자 이하로 입력하세요.")
    private String password;

    private String introduce;

}
