package com.sparta.codesourcecommunity.member.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class ModifyPasswordDto {

    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}+$", message = "비밀번호는 알파벳 대,소문자, 숫자로만 구성되며, 8자 이상 15자 이하입니다")
    private String password;
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}+$", message = "비밀번호를 알파벳 대,소문자, 숫자로만 구성되며, 8자 이상 15자 이하로 입력하세요.")
    private String changePassword;
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}+$", message = "비밀번호를 알파벳 대,소문자, 숫자로만 구성되며, 8자 이상 15자 이하로 입력하세요.")
    private String ChangePasswordConfirm;

}
