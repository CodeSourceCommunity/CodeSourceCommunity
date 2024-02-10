package com.sparta.codesourcecommunity.member.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class MemberLoginRequestDto {
    @Email(message = "email 형식으로 입력하세요.")
    private String email;
    private String password;
}
