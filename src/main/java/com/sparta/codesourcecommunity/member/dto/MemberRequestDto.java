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
    @Pattern(regexp = "^[a-z0-9]*$")
    private String nickname;
    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
    private String password;

    private String introduce;

}
