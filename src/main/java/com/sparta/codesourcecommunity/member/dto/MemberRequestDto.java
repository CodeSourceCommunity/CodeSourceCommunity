package com.sparta.codesourcecommunity.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDto {

    @Email
    private String email;
    @Pattern(regexp = "^[0-9a-zA-Z]$")
    private String nickname;
    @Pattern(regexp = "^[0-9a-zA-Z]{8,15}$")
    private String password;

}
